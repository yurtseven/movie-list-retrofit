package com.emreyurtseven.retrofit_movie_list.Model

data class MoviesModel (
    val status: Int?,
    val result: List<resultMoviesModel>?
        ){
    data class resultMoviesModel (
        val name: String?,
        val director: String?,
        val image: String?,
        val description: String?
            ){
    }
}