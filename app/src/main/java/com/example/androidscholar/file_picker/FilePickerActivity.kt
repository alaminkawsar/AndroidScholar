package com.example.androidscholar.file_picker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.androidscholar.R
import com.example.androidscholar.databinding.CustomDrawingViewBinding
import com.example.androidscholar.databinding.FilePickerLayoutBinding
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class FilePickerActivity: ComponentActivity() {

    private lateinit var binding: FilePickerLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        binding = FilePickerLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.showButton.setOnClickListener() {
            chooseMedia()
        }
    }
    private fun chooseMedia() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, REQUEST_CODE)
    }

    private fun getFileType(uri: Uri): String? {
        val contentResolver = contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri))
    }
    private fun copyInputStreamToFile(inputStream: InputStream, file: File) {
        try {
            FileOutputStream(file,false).use { outputStream ->
                var read: Int
                val bytes = ByteArray(DEFAULT_BUFFER_SIZE)
                while (inputStream.read(bytes).also { read = it } != -1) {
                    outputStream.write(bytes, 0, read)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val uri: Uri? = data?.data
            if (uri != null) {
                val file = File(cacheDir, "video.${getFileType(uri)}")
                copyInputStreamToFile(contentResolver.openInputStream(uri)!!, file)
                Toast.makeText(this, "File saved at ${file.absolutePath}", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        private const val TAG = "FilePickerActivity"
        const val REQUEST_CODE = 50
    }
}