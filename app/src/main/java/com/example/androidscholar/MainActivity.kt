package com.example.androidscholar

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.androidscholar.databinding.CustomDrawingViewBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: CustomDrawingViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        binding = CustomDrawingViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}