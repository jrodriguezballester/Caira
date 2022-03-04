package com.example.caira15

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendMessage(this)
    }

    fun sendMessage(view: MainActivity) {
     //   val editText = findViewById<EditText>(R.id.editTextTextPersonName)
     //   val message = editText.text.toString()
        val intent = Intent(this, Instrucciones::class.java)
        startActivity(intent)
    }
}
