package com.example.caira15.fragments

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseFragment<T : ViewBinding>(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    protected var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}


