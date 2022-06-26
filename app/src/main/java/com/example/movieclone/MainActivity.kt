package com.example.movieclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieclone.databinding.ActivityMainBinding
import com.example.movieclone.ui.HomeFragment
import com.example.movieclone.ui.SearchFragment
import com.example.movieclone.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        val mview = binding.root
        setContentView(mview)
        val fragmentId = R.id.fragmentContainerView

        var checkFragment = supportFragmentManager.findFragmentById(fragmentId)

        if(checkFragment == null){
            val homeFragment = HomeFragment()
            val fm = supportFragmentManager
            fm.beginTransaction()
                .add(fragmentId,homeFragment,HomeFragment.HomeFragmentTag)
                .commit()
        }

    }
}