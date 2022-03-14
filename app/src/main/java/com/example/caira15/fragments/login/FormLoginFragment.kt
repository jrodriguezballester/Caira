package com.example.caira15.fragments.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.caira15.R
import com.example.caira15.api.APIResponseLogin
import com.example.caira15.api.APIService
import com.example.caira15.api.RetrofitHelper
import com.example.caira15.databinding.FragmentFormLoginBinding
import com.example.caira15.fragments.BaseFragment

import com.example.caira15.model.DataLogin
import com.example.caira15.ui.Register.RegisterActivity
import com.example.caira15.ui.dashboard.DashboardActivity
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

        retrofit = RetrofitHelper.getRetrofit()
        service = retrofit.create(APIService::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFormLoginBinding.bind(view)
        var email = binding.editTextTextEmailAddress.text.toString()
        var password = binding.editTextTextPassword.text.toString()

        binding.btnLogin.setOnClickListener {
            //TODO DESCOMENTAR EN PRODUCCION
            //////TEST//////

            //TODO COMPROBAR NULOS Y FORMATOS EMAIL-PASSWORD//
            ////   var dataLogin=DataLogin(email,password)
            var dataLogin = DataLogin("jrodriguezballester@gmail.com", "caira")
            //caira==  $2a$10$qh0eG4WwVD5eftGL38dP.e1wlAqiItN6T5HPfBTer5tDt70p98DTi
            getLogin(dataLogin)
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(activity, RegisterActivity::class.java)
            activity?.startActivity(intent)
        }
        binding.tvForgot.setOnClickListener {
            //TODO MANDAR EMAIL
            Toast.makeText(context, "HAS OLVIDADO LA CONTRASEÑA", Toast.LENGTH_LONG).show()

//           email = binding.editTextTextEmailAddress.text.toString()
//            if (email!=null){
//                //Mandar Correo
//                Toast.makeText(context, "mandando correo", Toast.LENGTH_LONG).show()
//                composeEmail(email)
//            }
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
            Log.i("requestBody:", requestBody.toString())
            val respons: APIResponseLogin? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    Log.i(TAG_LOGS, call.toString())
                    Log.i(TAG_LOGS, respons.toString())
                    if(respons!!.status ==200){
                        //TODO GUARDAR TOKEN Y DATOS PASAR A DASHBOARD
                        Toast.makeText(context, respons.msg, Toast.LENGTH_LONG).show()
                        val intent = Intent(activity, DashboardActivity::class.java)
                        activity?.startActivity(intent)
                    }else{
                        //TODO CONTROLAR TODOS MENSAJES DE ERROR
                        Toast.makeText(context, respons.msg, Toast.LENGTH_LONG).show()
                    }
                } else {
                    //
                    Toast.makeText(context, "Problemas con el servidor o internet", Toast.LENGTH_LONG).show()

                    Log.i(TAG_LOGS, "no ------- llego aqui------------")
                }
            }
        }
    }


    fun composeEmail(address: String) {
        val subject="Caira"
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, address)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
            startActivity(intent)
    }
}