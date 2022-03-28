package com.example.caira15.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.caira15.CairaAplication.Companion.prefs

class ViewModelBody:ViewModel(){
    var status = MutableLiveData<Boolean?>()
    fun logout() {
        prefs.closeSesion()
        status.value=true

    }


}