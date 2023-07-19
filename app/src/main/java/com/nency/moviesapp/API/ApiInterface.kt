package com.nency.moviesapp.API

import com.nency.moviesapp.Model.UpcomingMovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("upcoming")
    fun getUpcomingMovies(
        @Query("page") page:Int
    ) : Call<UpcomingMovieModel>

    @GET("popular")
    fun getPopularMovies(
        @Query("page") page:Int
    ) : Call<UpcomingMovieModel>

    @GET("now_playing")
    fun getNowPlayingMovies(
        @Query("page") page:Int
    ) : Call<UpcomingMovieModel>

    @GET("top_rated")
    fun getTopRatedMovies(
        @Query("page") page:Int
    ) : Call<UpcomingMovieModel>
}