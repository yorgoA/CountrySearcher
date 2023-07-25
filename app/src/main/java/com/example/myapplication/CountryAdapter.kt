package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CountryCardBinding
import com.squareup.picasso.Picasso

class CountryAdapter(private val countries: List<ApiResponse>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.binding.textView.text = country.name?.common
        Picasso.get().load(country.flags?.png).into(holder.binding.imageview)  // Loading flag using Picasso
    }

    override fun getItemCount() = countries.size

    class CountryViewHolder(val binding: CountryCardBinding) : RecyclerView.ViewHolder(binding.root)
}
