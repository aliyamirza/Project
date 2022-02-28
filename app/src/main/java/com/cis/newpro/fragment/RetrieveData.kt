package com.cis.newpro.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cis.newpro.R
//import com.cis.newpro.User
import com.cis.newpro.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_retrieve_data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.StringBuilder


class RetrieveData : Fragment(), View.OnFocusChangeListener {

    val person = FirebaseFirestore.getInstance().collection("User")
    val auth = FirebaseAuth.getInstance()
    val currentUser= auth.currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_retrieve_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        btnRetriveData.onFocusChangeListener = this


        btnRetriveData.setOnClickListener {
            retrievedata()

        }
        super.onViewCreated(view, savedInstanceState)
    }
//
//    private fun reaTimeUpdate() {
//
//        person.addSnapshotListener { QuerySnapshot, FirebaseFirestoreException ->
//            FirebaseFirestoreException?.let {
//                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
//                return@addSnapshotListener
//            }
//            QuerySnapshot?.let {
//                var sb = StringBuilder()
//                for (documents in it) {
//                    var user = documents.toObject<Users>()
//                    sb.append("$user")
//                }
//                txtdData.text = sb.toString()
//            }
//
//        }
//    }


        private fun retrievedata() = CoroutineScope(Dispatchers.IO).launch {

            try {
                var querySnapshot = person.whereEqualTo("uid",currentUser).get().await()
                val sb = StringBuilder()
                val sbr = StringBuilder()
                for (documents in querySnapshot.documents) {
                    val email = documents.get("email")
                    val password = documents.get("password")
                    val fullname = documents.get("fullname")
                    sb.append("$email\n $password\n $fullname\n")
                }
                withContext(Dispatchers.Main){
                    txtdData.text = sb.toString()
                    txtdDataa.text = sbr.toString()
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
