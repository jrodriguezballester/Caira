package com.example.caira15.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.caira15.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendMessage(this)
    }

    fun sendMessage(view: MainActivity) {

        val intent = Intent(this, InstruccionesActivity::class.java)
        startActivity(intent)
    }
}
