package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Response

class GameActivity : AppCompatActivity() {

    private lateinit var gameAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Setting up the RecyclerView
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        gameAdapter = GameAdapter(emptyList())
        recyclerView.adapter = gameAdapter

        // Fetching the data
        fetchData()
    }

    private fun fetchData() {
        val service = GameBuilder.buildService(GameInterface::class.java)
        val call = service.getGeographyQuestions()   // Modified this line
        call.enqueue(object : retrofit2.Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                if (response.isSuccessful) {
                    val questionList = response.body() ?: emptyList()
                    gameAdapter.questions = questionList
                    gameAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                // Handle the error, maybe show a Toast or Snackbar
            }
        })
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
}
