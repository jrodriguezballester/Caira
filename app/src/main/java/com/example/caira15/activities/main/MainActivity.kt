package com.example.caira15.activities.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.caira15.CairaAplication.Companion.prefs
import com.example.caira15.R
import com.example.caira15.activities.principal.BodyappActivity
import com.example.caira15.activities.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  prefs.closeSesion()  // to debuger
        //   Log.i("prefs(clear):", prefs.getResult().toString())

        // in debuger descoment
        // goToDasboard(this)

        // in debuger coment
           isLogin()

    }
    //supportFragmentManager.beginTransaction().replace(R.id.contenedorFragment,ListProgramFragment.newInstance()).commit()

    private fun isLogin() {
        //TODO verificar el token
        if (!prefs.getResult().isNullOrEmpty()) {
            Log.i("prefs:", prefs.getResult().toString())
            goToDasboard(this)
        } else {
            goToLogin(this)
        }
    }

    fun goToDasboard(mainActivity: MainActivity) {
        val intent = Intent(this, BodyappActivity::class.java)
        startActivity(intent)
    }

    fun goToLogin(view: MainActivity) {

        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }
}
