package com.example.caira15.fragments

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SearchViewModel : ViewModel() {
    var status = MutableLiveData<Boolean>()

    fun loginOnCLicked(){
        status.value=true
    }
    fun loginOnCLicked(v: View){
    status.value=true
     //   progress.setValue(0); //View.VISIBLE

    //    progress.setValue(2); //View.GONE

    }
    fun invisibleUnless(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}
//object BindingAdapters {
//    @BindingAdapter("invisibleUnless")
//    @JvmStatic
//    fun invisibleUnless(view: View, visible: Boolean) {
//        view.visibility = if (visible) View.VISIBLE else View.GONE
//    }
// }