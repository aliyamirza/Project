package com.cis.newpro.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cis.newpro.R
import com.cis.newpro.Users
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


class SignUp : Fragment(), View.OnFocusChangeListener {

    val person = FirebaseFirestore.getInstance().collection("User")
    var auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        edtEmailAddress.onFocusChangeListener = this
        edtUsername.onFocusChangeListener = this
        edtPassword.onFocusChangeListener = this
        edtConfirmPass.onFocusChangeListener = this
        btnSignup.onFocusChangeListener = this
        log.onFocusChangeListener=this

        log.setOnClickListener {

            val login=Login()
            fragmentManager!!.beginTransaction().replace(R.id.fragmentContainer,login).commit()

        }

        btnSignup.setOnClickListener {
            val uid =auth.currentUser?.uid
            val email = edtEmailAddress.text.toString()
            val username = edtEmailAddress.text.toString()
            val password = edtEmailAddress.text.toString()

            if (validatee()){
                val user = Users(userID = uid.toString(),email = email,username = username,password = password)
                addUserData(user)

            }
        }
    }

    private fun addUserData(user: Users) = CoroutineScope(Dispatchers.IO).launch {

        val email = edtEmailAddress.text.toString()
        val password = edtPassword.text.toString()
        try {
            person.add(user).await()
            auth.createUserWithEmailAndPassword(email, password)
            startActivity(Intent(context,Login::class.java))
            withContext(Dispatchers.Main){
                Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
            }

        }catch (e : Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validatee(): Boolean {

        return when {
            edtEmailAddress!!.text!!.isEmpty() -> {
                edtEmailAddress.setError("Enter Valid Email")
                false
            }
            edtUsername!!.text!!.isEmpty()->
            {
                edtUsername.setError("Enter your name")
                false
            }
            edtPassword!!.text!!.isEmpty() -> {
                edtPassword.setError("Enter Valid Password")
                false
            }

            else -> true
        }

    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
    }

}



//private fun onLogin() {
//
////        db.collection("Users")
////            .get()
////            .addOnCompleteListener {
////                if (it.isSuccessful)
////                {
////                    for (data in it.result!!)
////                    {
////                        Toast.makeText(context, "${data.getData()}", Toast.LENGTH_SHORT).show()
////                    }
////                }
////                else{
////                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
////                }
////            }
//
//    //For Insert Data In Firebase
//    val email = edtEmailAddress.text.toString()
//    val password=edtPassword.text.toString()
//    val name=edtUsername.text.toString()
//
//    val user: MutableMap<String, Any> = HashMap()
//    user.put("email","$email")
//    user.put("name","$name")
//    user.put("password","$password")
//    db.collection("Users")
//        .add(user)
//        .addOnSuccessListener {
//            Toast.makeText(context, "Successfully Inserted", Toast.LENGTH_SHORT).show()
//        }
//        .addOnFailureListener {
//            Toast.makeText(context, " Faield ", Toast.LENGTH_SHORT).show()
//        }
//
//
//
//    // val login = Login()
//    //  fragmentManager!!.beginTransaction().replace(R.id.fragmentContainer, login).commit()
//
//}