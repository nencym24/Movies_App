package com.nency.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nency.moviesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var Type = arrayOf("Now Playing","Popular","TopRated","Up Coming")
        var Fragments = arrayOf(NowPlayingFragment(),PopularFragment(),TopRatedFragment(),UpcomingFragment())
        loadFragment(NowPlayingFragment())
        binding.bottomnav.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when(item.itemId) {
                    R.id.nowPlaying->{
                        loadFragment(NowPlayingFragment())
                    }
                }
                when(item.itemId) {
                    R.id.popular->{
                        loadFragment(PopularFragment())
                    }
                }
                when(item.itemId) {
                    R.id.topRated->{
                        loadFragment(TopRatedFragment())
                    }
                }
                when(item.itemId) {
                    R.id.upComing->{
                        loadFragment(UpcomingFragment())
                    }
                }

                return true
            }
        })
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

}