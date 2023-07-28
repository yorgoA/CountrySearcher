package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.Toolbar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val allCountries = ArrayList<ApiResponse>()
    private val filteredCountries = ArrayList<ApiResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                filterCountries(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })

        getAllCountries()
    }

    private fun filterCountries(query: String) {
        filteredCountries.clear()
        for (country in allCountries) {
            country.name?.common?.let {
                if (it.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))) {
                    filteredCountries.add(country)
                }
            }
        }
        val adapter = CountryAdapter(filteredCountries)
        binding.recyclerview.adapter = adapter
    }

    private fun getAllCountries() {
        val retrofit = ServiceBuilder.buildService(ServiceInterface::class.java)
        retrofit.getAllCountries().enqueue(object : retrofit2.Callback<List<ApiResponse>> {
            override fun onResponse(call: Call<List<ApiResponse>>, response: Response<List<ApiResponse>>) {
                if (response.isSuccessful && response.body() != null) {
                    val responseBody = response.body()!!
                    Log.d("MainActivity", "Number of countries fetched: ${responseBody.size}")
                    allCountries.clear()
                    allCountries.addAll(responseBody)
                    filteredCountries.addAll(responseBody)
                    val adapter = CountryAdapter(filteredCountries)
                    binding.recyclerview.adapter = adapter
                } else {
                    Log.e("MainActivity", "Response was not successful or body is null")
                }
            }

            override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
                Log.e("MainActivity", "Api call failed", t)
            }
        })
    }
}
