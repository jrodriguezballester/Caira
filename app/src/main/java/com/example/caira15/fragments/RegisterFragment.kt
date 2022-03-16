package com.example.caira15.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.caira15.R
import com.example.caira15.api.APIResponseSingUpUser
import com.example.caira15.api.APIService
import com.example.caira15.api.RetrofitHelper
import com.example.caira15.databinding.FragmentRegisterBinding
import com.example.caira15.model.DataRegister
import com.example.caira15.ui.login.InstruccionesActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {
    val TAG_LOGS = "*********************************"

    private lateinit var retrofit: Retrofit
    private lateinit var service: APIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrofit = RetrofitHelper.getRetrofit()
        service = retrofit.create(APIService::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)

        binding.buttonLoginInRegister.setOnClickListener {
            val intent = Intent(activity, InstruccionesActivity::class.java)
            activity?.startActivity(intent)
        }
        binding.buttonRegister.setOnClickListener {
            //Recoger Campos
            var name = binding.Name.text.toString()
            var mail = binding.email.text.toString()
            var password = binding.password.text.toString()
            var rpassword = binding.rpassword.text.toString()

            //TODO VERIFICAR CAMPOS
            var dataRegister = DataRegister(name, mail, password, rpassword)
            singUp(dataRegister)

        }
    }


    private fun singUp(dataRegister: DataRegister) {
        Log.i("dataRegister: ", dataRegister.toString())
        CoroutineScope(Dispatchers.IO).launch {
            val call = service.singUp(dataRegister)
            val respons: APIResponseSingUpUser? = call.body()
            activity?.runOnUiThread {
                if (call.isSuccessful) {
                    //TODO VERIFICAR RESPUESTAS
                    Log.i("**********call", call.toString())
                    Log.i(TAG_LOGS, respons.toString())
                    Log.i("**********respuesta", respons?.result.toString())
                } else {
                    Log.i(TAG_LOGS, "no ------- llego aqui------------")
                }
            }
        }
    }

}