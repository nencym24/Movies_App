package com.nency.moviesapp

import android.graphics.Movie
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.nency.moviesapp.API.ApiClient
import com.nency.moviesapp.API.ApiInterface
import com.nency.moviesapp.Adapter.UpcomingAdapter
import com.nency.moviesapp.Model.ResultsItem
import com.nency.moviesapp.Model.UpcomingMovieModel
import com.nency.moviesapp.databinding.FragmentNowPlayingBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class NowPlayingFragment : Fragment() {

    lateinit var binding : FragmentNowPlayingBinding
    var adapter = UpcomingAdapter()
    var page = 1
    var movieList = ArrayList<ResultsItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNowPlayingBinding.inflate(layoutInflater)


        binding.nested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {

                page++
                callApi(page)
            }
        })


        callApi(page)

        return binding.root

    }

    private fun callApi(page: Int) {
        var api = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getNowPlayingMovies(page).enqueue(object : retrofit2.Callback<UpcomingMovieModel> {
            override fun onResponse(
                call: Call<UpcomingMovieModel>,
                response: Response<UpcomingMovieModel>
            ) {

                if (response.isSuccessful) {
                    var NowPlayingList = response.body()?.results

                    movieList.addAll(NowPlayingList as List<ResultsItem>)

                    adapter.setListing(movieList)
                    binding.rcvNowPlaying.layoutManager = LinearLayoutManager(context)
                    binding.rcvNowPlaying.adapter = adapter
                }
            }

            override fun onFailure(call: Call<UpcomingMovieModel>, t: Throwable) {

            }

        })
    }
}