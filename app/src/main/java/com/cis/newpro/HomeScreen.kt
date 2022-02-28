package com.cis.newpro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cis.newpro.fragment.Login
import com.cis.newpro.fragment.SignUp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home_screen.*

class HomeScreen : Fragment(), View.OnFocusChangeListener {

    //val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       // btnLogOut.onFocusChangeListener = this
//
//        btnLogOut.setOnClickListener {
//            auth.signOut()
          // val login = Login()
        //    fragmentManager!!.beginTransaction().replace(R.id.fragmentContainer, login).commit()
//        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {

    }

}