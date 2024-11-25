package com.example.androidscholar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidscholar.databinding.LayoutBinding
import com.example.androidscholar.file_picker.FilePickerUi

class MainActivity : ComponentActivity() {
    private lateinit var binding: LayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilePickerUi()
        }
    }
}