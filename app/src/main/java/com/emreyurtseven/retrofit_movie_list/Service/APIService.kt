package com.emreyurtseven.retrofit_movie_list.Service

import com.emreyurtseven.retrofit_movie_list.Model.MoviesModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    //https://raw.githubusercontent.com/yurtseven/JsonTest/main/movies.json
    @GET("JsonTest/main/movies.json")
    fun getMoviesModel(): Call<MoviesModel>
}