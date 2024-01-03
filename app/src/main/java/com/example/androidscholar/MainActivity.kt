package com.example.androidscholar

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var btnStart: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        btnStart = findViewById(R.id.show_button)

        btnStart.setOnClickListener { onClick(it) }
    }

    private fun onClick(view: View?) {
        Log.v("MainActivity", "Button Clicked ${view?.id==R.id.show_button}")
        val customView = layoutInflater.inflate(R.layout.customtoast_layout, findViewById(R.id.custom_toastId))
        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_LONG
        toast.view = customView
        toast.show()
    }
}