package com.cis.newpro.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cis.newpro.MainActivity
import com.cis.newpro.R
//import com.cis.newpro.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class Login : Fragment(), View.OnFocusChangeListener {


    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        emailId.onFocusChangeListener = this
        pass.onFocusChangeListener = this
        btnLoginX.onFocusChangeListener = this
        txtCreateAccount.onFocusChangeListener = this

        txtCreateAccount.setOnClickListener {
            val signup = SignUp()
            fragmentManager!!.beginTransaction().replace(R.id.fragmentContainer, signup).commit()

        }

        forgot.setOnClickListener {
            val resetPassword = ResetPassword()
            fragmentManager!!.beginTransaction().replace(R.id.fragmentContainer,resetPassword).commit()

        }

        btnLoginX.setOnClickListener {

            if (validation()) loginUser()
        }

    }


    private fun validation(): Boolean {

        return when {
            emailId!!.text!!.isEmpty() -> {
                emailId.setError("Email is required")
                false
            }
            pass!!.text!!.isEmpty() -> {
                edtPassword.setError("password is required")
                false
            }

            else -> true

        }
    }



    private fun loginUser() = CoroutineScope(Dispatchers.IO).launch {

        try {
            auth.signInWithEmailAndPassword(emailId.text.toString(), pass.text.toString())
            withContext(Dispatchers.Main) {
                startActivity(Intent(context,MainActivity::class.java))
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }



    override fun onFocusChange(v: View?, hasFocus: Boolean) {

    }


}