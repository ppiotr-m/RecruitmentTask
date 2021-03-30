package com.example.recruitmenttask.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.recruitmenttask.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        createBinding()
        setupNavController()
    }

    private fun setupNavController() {
        navController = ((supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
            ?: return) as NavHostFragment).navController
    }

//    private fun createBinding() {
//        binding =
//            DataBindingUtil.setContentView(this, R.layout.activity_main)
//    }
}