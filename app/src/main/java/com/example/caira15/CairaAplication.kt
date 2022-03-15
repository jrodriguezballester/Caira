package com.example.caira15

import android.app.Application
import com.example.caira15.model.Prefs

class CairaAplication:Application (){
    companion object{
        lateinit var prefs: Prefs
    }
    override fun onCreate() {
        super.onCreate()
        prefs= Prefs(applicationContext)
    }
}