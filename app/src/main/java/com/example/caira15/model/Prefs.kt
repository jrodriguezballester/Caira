package com.example.caira15.model

import android.content.Context
import android.util.Log

class Prefs(val context:Context) {

    val SHARED_NAME ="Mydtb"
    val SHARED_UserId ="userId"
    val SHARED_ROL ="Rol"
    val SHARED_TOKEN ="Token"

    val storage=context.getSharedPreferences(SHARED_NAME,0)

    fun saveResult(rol: Int,userId:String,token:String){
        storage.edit().putInt(SHARED_ROL,rol).apply()
        storage.edit().putString(SHARED_UserId,userId).apply()
        storage.edit().putString(SHARED_TOKEN,token).apply()
    }

    fun getResult(): MutableMap<String, *>? {
        return storage.all
    }
    fun closeSesion(){
        storage.edit().clear().apply()
        Log.i("prefs(clear):", storage.toString())
    }
}