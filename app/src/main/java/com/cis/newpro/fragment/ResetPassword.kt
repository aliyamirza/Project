package com.cis.newpro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cis.newpro.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_reset_password.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.coroutines.CoroutineContext


class ResetPassword : Fragment() {

    var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reset_password, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        btnReset.setOnClickListener {

            forgotPass()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun forgotPass() = CoroutineScope(Dispatchers.IO).launch {

        val email = edtResetEmail.text.toString()
        try {
            if (email.isEmpty()) {
                Toast.makeText(context, "Enter your email!", Toast.LENGTH_SHORT).show()
            } else {
                auth!!.sendPasswordResetEmail(edtResetEmail.text.toString())
            withContext(Dispatchers.Main){
                Toast.makeText(context,"Check email to reset your password!", Toast.LENGTH_SHORT).show()
            }}
//                        .addOnCompleteListener { task ->
//                            if (task.isSuccessful) {
//                                Toast.makeText(context, "Check email to reset your password!", Toast.LENGTH_SHORT).show()
//                            } else {
//                                Toast.makeText(context, "Fail to send reset password email!", Toast.LENGTH_SHORT).show()
//                            }
//                        }
            
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//        val email =edtResetEmail.text.toString()
//
//        try {
//            if (email.isNotEmpty()){
//                auth.sendPasswordResetEmail(email).await()
//                Toast.makeText(context, "Please Check Your Email For Reset Password", Toast.LENGTH_SHORT).show()
//            }else{
//
//                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
//
//            }
//
//        }catch (e:Exception){
//            withContext(Dispatchers.Main){
//                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }

