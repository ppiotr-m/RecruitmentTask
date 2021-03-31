package com.example.recruitmenttask.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.recruitmenttask.R
import com.example.recruitmenttask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setupNavController()
    }

    private fun setupNavController() {
        navController = ((supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
            ?: return) as NavHostFragment).navController
    }

    fun setToolbarText(textArg: String) {
        findViewById<TextView>(R.id.toolbarTV).text = textArg
    }
}