package com.example.caira15.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.caira15.CairaAplication.Companion.prefs
import com.example.caira15.R
import com.example.caira15.activities.welcome.WelcomeActivity
import com.example.caira15.activities.principal.BodyappActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      prefs.closeSesion()
        goToDasboard(this)
   //   Log.i("prefs(clear):", prefs.getResult().toString())
     //   isLogin()
//supportFragmentManager.beginTransaction().replace(R.id.contenedorFragment,ListProgramFragment.newInstance()).commit()
    }

    private fun isLogin() {
        //TODO verificar el token
        if(!prefs.getResult().isNullOrEmpty()){
            Log.i("prefs:", prefs.getResult().toString())
            goToDasboard(this)
        }else{
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
