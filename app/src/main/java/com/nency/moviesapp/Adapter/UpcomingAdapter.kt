package com.nency.moviesapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.nency.moviesapp.API.ApiClient
import com.nency.moviesapp.Model.ResultsItem
import com.nency.moviesapp.databinding.MovieItemBinding

class UpcomingAdapter : RecyclerView.Adapter<UpcomingAdapter.UpcomingHolder>(){

    lateinit var list: List<ResultsItem>
    lateinit var context: Context

    class UpcomingHolder(itemView: MovieItemBinding) : ViewHolder(itemView.root){

        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingHolder {
        context = parent.context
        var binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UpcomingHolder(binding)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: UpcomingHolder, position: Int) {

        holder.binding.apply {
                list.get(position).apply {

                    Glide.with(context).load(ApiClient.IMAGE_BASE_URL+posterPath).into(imgPoster)
                    txtTitle.text = originalTitle
                    txtDesc.text =overview
                    txtPopularity.text = popularity.toString()
                    txtReleaseDate.text = releaseDate
                    txtAdult.text = adult.toString()
                    txtVotes.text = voteAverage.toString()
                    txtOriginalLang.text = originalLanguage
                }
        }
    }

    fun setListing(list: List<ResultsItem>) {
        this.list = list as List<ResultsItem>

    }

}