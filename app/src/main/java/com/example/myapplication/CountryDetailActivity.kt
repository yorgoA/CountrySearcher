package com.example.myapplication

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.TextView
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan


class CountryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvOfficialName: TextView = findViewById(R.id.tvOfficialName)
        val tvRegionSubregion: TextView = findViewById(R.id.tvRegionSubregion)
        val tvCapital: TextView = findViewById(R.id.tvCapital)
        val tvPopulation: TextView = findViewById(R.id.tvPopulation)
        val tvCurrency: TextView = findViewById(R.id.tvCurrency)
        val tvLanguages: TextView = findViewById(R.id.tvLanguages)

        val countryData: ApiResponse? = intent.getParcelableExtra("countryData") as? ApiResponse

        countryData?.let { country ->
            tvOfficialName.text = country.name?.official

            setBoldTitle(tvRegionSubregion, "Region", "${country.region}, ${country.subregion}")
            setBoldTitle(tvCapital, "Capital", country.capital?.joinToString(", "))
            setBoldTitle(tvPopulation, "Population", country.population.toString())
            setBoldTitle(tvCurrency, "Currency", country.currencies?.values?.first()?.name)
            setBoldTitle(tvLanguages, "Languages", country.languages?.values?.joinToString(", "))
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun setBoldTitle(textView: TextView, title: String, content: String?) {
        val finalString = "$title: $content"
        val spannableString = SpannableString(finalString)

        val boldSpan = StyleSpan(Typeface.BOLD)
        spannableString.setSpan(boldSpan, 0, title.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannableString
    }


}
