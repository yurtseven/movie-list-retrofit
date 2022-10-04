package com.emreyurtseven.retrofit_movie_list.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emreyurtseven.retrofit_movie_list.View.MovieDetails
import com.emreyurtseven.retrofit_movie_list.databinding.MoviesRowBinding
import com.squareup.picasso.Picasso

class MoviesAdapter(val movieName: ArrayList<String>,
                    val movieImage: ArrayList<String>,
                    val movieDirector: ArrayList<String>,
                    val movieDescription: ArrayList<String>
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    class MoviesHolder(val binding: MoviesRowBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val binding = MoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.binding.moviesItemTitle.text = movieName.get(position)
        Picasso.get().load(movieImage.get(position)).into(holder.binding.moviesItemImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetails::class.java)
            intent.putExtra("name",movieName.get(position))
            intent.putExtra("director",movieDirector.get(position))
            intent.putExtra("image",movieImage.get(position))
            intent.putExtra("description",movieDescription.get(position))
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return movieName.size
    }
}