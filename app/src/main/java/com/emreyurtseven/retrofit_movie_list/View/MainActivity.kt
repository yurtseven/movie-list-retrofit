package com.emreyurtseven.retrofit_movie_list.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emreyurtseven.retrofit_movie_list.Adapter.MoviesAdapter
import com.emreyurtseven.retrofit_movie_list.Model.MoviesModel
import com.emreyurtseven.retrofit_movie_list.Service.APIService
import com.emreyurtseven.retrofit_movie_list.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    //https://raw.githubusercontent.com/yurtseven/JsonTest/main/movies.json

    private lateinit var binding: ActivityMainBinding
    private var movieName = ArrayList<String>()
    private var movieDirector = ArrayList<String>()
    private var movieImage = ArrayList<String>()
    private var movieDescription = ArrayList<String>()
    private val BASE_URL = "https://raw.githubusercontent.com/yurtseven/"

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MoviesAdapter.MoviesHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadData()
    }

    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)


        val retrofitData = retrofit.getMoviesModel()
        retrofitData.enqueue(object : Callback<MoviesModel> {
            override fun onResponse(call: Call<MoviesModel>, response: Response<MoviesModel>) {
                if (response.isSuccessful) {
                    val moviesResult = response.body()?.result
                    if (moviesResult != null) {
                        for (movies in moviesResult) {
                            if (movies.name != null) {
                                movieName.add(movies.name)
                            }
                            if (movies.director != null) {
                                movieDirector.add(movies.director)
                            }
                            if (movies.image != null) {
                                movieImage.add(movies.image)
                            }
                            if (movies.description != null) {
                                movieDescription.add(movies.description)
                            }
                        }
                    }
                }
                layoutManager = LinearLayoutManager(this@MainActivity)
                val recyclerView : RecyclerView = binding.recyclerView
                recyclerView.layoutManager = layoutManager

                adapter = MoviesAdapter(movieName,movieImage, movieDirector, movieDescription)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<MoviesModel>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }
}
