package com.example.cleanarchitectureexample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.cleanarchitectureexample.R
import com.example.cleanarchitectureexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() ||
                findNavController(R.id.nav_host_fragment).navigateUp()
    }
}