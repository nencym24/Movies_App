package com.nency.moviesapp

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
import com.nency.moviesapp.databinding.FragmentUpcomingBinding
import retrofit2.Call
import retrofit2.Response

class UpcomingFragment : Fragment() {

    lateinit var binding : FragmentUpcomingBinding
    var adapter = UpcomingAdapter()
    var page = 1
    var movieList = ArrayList<ResultsItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpcomingBinding.inflate(layoutInflater)


        binding.nested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {

                page++
                callApi(page)
            }
        })


        callApi(page)


        return  binding.root
    }

    private fun callApi(page: Int) {

        var api = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getUpcomingMovies(page).enqueue(object : retrofit2.Callback<UpcomingMovieModel> {
            override fun onResponse(
                call: Call<UpcomingMovieModel>,
                response: Response<UpcomingMovieModel>
            ) {

                if (response.isSuccessful) {
                    var UpcomingList = response.body()?.results

                    movieList.addAll(UpcomingList as List<ResultsItem>)

                    adapter.setListing(movieList)
                    binding.rcvUpcoming.layoutManager = LinearLayoutManager(context)
                    binding.rcvUpcoming.adapter = adapter
                }
            }

            override fun onFailure(call: Call<UpcomingMovieModel>, t: Throwable) {

            }

        })
    }
}