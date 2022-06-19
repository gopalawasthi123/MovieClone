package com.example.movieclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.movieclone.databinding.ActivityMainBinding
import com.example.movieclone.ui.HomeFragment
import com.example.movieclone.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        val mview = binding.root
        setContentView(mview)

        val homeFragment = HomeFragment()
        val fm = supportFragmentManager
        fm.beginTransaction()
            .add(R.id.fragmentContainerView,homeFragment,"HomeFragment")
            .addToBackStack(null)
            .commit()
    }
}