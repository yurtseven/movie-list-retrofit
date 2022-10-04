package com.emreyurtseven.retrofit_movie_list.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emreyurtseven.retrofit_movie_list.databinding.ActivityMovieDetailsBinding
import com.squareup.picasso.Picasso

class MovieDetails : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent
        val name = intent.getStringExtra("name").toString()
        val director = intent.getStringExtra("director").toString()
        val image = intent.getStringExtra("image").toString()
        val description = intent.getStringExtra("description").toString()

        binding.movieNameTitle.text = name
        binding.movieDirectorTitle.text = "Director: "+ director
        binding.movieDetailsDescription.text = description
        Picasso.get().load(image).into(binding.movieDetailsImage)

    }
}