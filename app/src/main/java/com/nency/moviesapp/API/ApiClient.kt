package com.nency.moviesapp.API

import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        val BASE_URL = "https://api.themoviedb.org/3/movie/"
        lateinit var retrofit : Retrofit
        val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMmFjZTM1MzU5ZjFlMDZkNWNhZjMyMTkxZWE1M2M1NSIsInN1YiI6IjY0YWI4M2MyNjZhMGQzMDExZDdiZTRiYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.1pPINc_OMhv1C538j5uEvHsi2EqltjU1w1DhvPF_0lk"

        fun getApiClient(): Retrofit {

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor { Chain ->

                    val request = Chain.request().newBuilder().addHeader("Authorization","Bearer ${TOKEN}").build()
                                    Chain.proceed(request)
                }.build())
                .build()
            return retrofit
        }


    }
}