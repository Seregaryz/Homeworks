package com.example.homeworks

import android.app.Activity
import android.content.Intent
import android.content.Intent.CATEGORY_DEFAULT
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

const val REQ_CODE = -1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onMoveCall(view: View){
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            addCategory(CATEGORY_DEFAULT)
            putExtra(Intent.EXTRA_TEXT, "http://vk.com")
            type = "text/plain"
        }
        startActivityForResult(sendIntent, REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Is Failed message", Toast.LENGTH_SHORT).show()
        }
    }
    
}