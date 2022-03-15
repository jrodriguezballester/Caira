package com.example.caira15.fragments.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.caira15.CairaAplication.Companion.prefs
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

        binding.btnLogin.setOnClickListener {
            var email = binding.editTextEmail.text.toString()
            var password = binding.editTextPassword.text.toString()
            var emailError:String?=null
            var passwordError:String?=null
            if (email.isEmpty()){
                emailError= getText(R.string.emailError).toString()
                binding.editTextEmail.error = emailError
            }
            if (password.isEmpty()){
                passwordError= getText(R.string.passwordError).toString()
                binding.editTextPassword.error = passwordError
            }
            //TODO FORMATOS EMAIL-PASSWORD//
            if (email.isNotEmpty() and password.isNotEmpty()) {
                var dataLogin = DataLogin(email, password)
                getLogin(dataLogin)
            } else {
                Toast.makeText(context, "Rellena los campos", Toast.LENGTH_LONG).show()
            }
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

    private fun toogleTextErrorEmail(emailError: String) {
        binding.editTextEmail.error = emailError
       // binding.editTextEmail.setErrorEnabled(emailError)

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
                    when (respons!!.status) {
                        200 -> {
                            //Login exitoso
                            Toast.makeText(context, respons.msg, Toast.LENGTH_LONG).show()
                            Log.i(TAG_LOGS, "result-----------:" + respons.result)
                            // guardar en SharePreferents
                            prefs.saveResult(respons.result.rol,respons.result.userId,respons.result.token)
                            goToDasboard()

                        }
                        400 -> {
                            //Mising Data
                            Toast.makeText(context, respons.msg, Toast.LENGTH_LONG).show()
                        }
                        404 -> {
                            //'Email or password is wrong.'
                            binding.editTextEmail.error = respons.msg
                            binding.editTextPassword.error=respons.msg
                     //       Toast.makeText(context, respons.msg, Toast.LENGTH_LONG).show()
                        }
                        422 -> {
                            //Wrong name
                            Toast.makeText(context, respons.msg, Toast.LENGTH_LONG).show()

                        }
                        502 -> {
                            //'Wrong email.
                            binding.editTextEmail.error = respons.msg
                          //  Toast.makeText(context, respons.msg, Toast.LENGTH_LONG).show()

                        }
                        else -> {
                            Toast.makeText(
                                context,
                                "Problemas con el servidor o internet",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.i(TAG_LOGS, "no ------- llego aqui------------")
                        }
                    }

                }
            }
        }


        fun composeEmail(address: String) {
            val subject = "Caira"
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, address)
                putExtra(Intent.EXTRA_SUBJECT, subject)
            }
            startActivity(intent)
        }
    }

    private fun goToDasboard() {
        val intent = Intent(activity, DashboardActivity::class.java)
        activity?.startActivity(intent)
    }
}