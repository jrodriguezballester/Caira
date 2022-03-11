package com.example.caira15.fragments.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.caira15.R
import com.example.caira15.api.APIResponseLogin
import com.example.caira15.api.APIService
import com.example.caira15.api.RetrofitHelper
import com.example.caira15.databinding.FragmentFormLoginBinding
import com.example.caira15.fragments.BaseFragment
import com.example.caira15.model.DataLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class FormLoginFragment : BaseFragment<FragmentFormLoginBinding>(R.layout.fragment_form_login) {


    private lateinit var retrofit: Retrofit
    private lateinit var service: APIService

    val TAG_LOGS = "*********************************"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
        val retrofit = RetrofitHelper.getRetrofit()
        service = retrofit.create(APIService::class.java)
        //     getLogin()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFormLoginBinding.bind(view)
        var email = binding.editTextTextEmailAddress.text.toString()
        var password = binding.editTextTextPassword.text.toString()

        binding.button.setOnClickListener {
            //TODO DESCOMENTAR EN PRODUCCION
            //////TEST//////
            //   var dataLogin=DataLogin(email,password
            var dataLogin = DataLogin("jose@gmail.com", "caira")
            //caira==  $2a$10$qh0eG4WwVD5eftGL38dP.e1wlAqiItN6T5HPfBTer5tDt70p98DTi
            getLogin(dataLogin)
        }
    }

    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
        activity?.runOnUiThread(action)
    }

    private fun getLogin(requestBody: DataLogin) {
        CoroutineScope(Dispatchers.IO).launch {
            // val call = retrofit.create(APIService::class.java).getAllPosts()
            val call = service.login(requestBody)
            val respons: APIResponseLogin? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    //show Recyclerview
                    Log.i(TAG_LOGS, call.toString())
                    Log.i(TAG_LOGS, respons.toString())
                    if(respons!!.status ==200){
                        //TODO GUARDAR TOKEN
                    }

                } else {
                    //show error
                    Log.i(TAG_LOGS, "no ------- llego aqui------------")
                }
            }
        }
    }
}