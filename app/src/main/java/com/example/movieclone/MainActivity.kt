package com.example.movieclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieclone.databinding.ActivityMainBinding
import com.example.movieclone.ui.HomeFragment
import com.example.movieclone.ui.SearchFragment
import com.example.movieclone.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        val mview = binding.root
        setContentView(mview)

        val homeFragment = HomeFragment()
        val fm = supportFragmentManager
        fm.beginTransaction()
            .add(R.id.fragmentContainerView,homeFragment)
            .commit()


    }

}