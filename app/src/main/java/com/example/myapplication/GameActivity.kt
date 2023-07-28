package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
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

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        gameAdapter = GameAdapter(emptyList())
        recyclerView.adapter = gameAdapter

        fetchData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game_activity, menu)
        return true
    }

    private fun fetchData() {
        val service = GameBuilder.buildService(GameInterface::class.java)
        val call = service.getGeographyQuestions()
        call.enqueue(object : retrofit2.Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                if (response.isSuccessful) {
                    val questionList = response.body() ?: emptyList()
                    gameAdapter.questions = questionList
                    gameAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                fetchData()
                return true
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}