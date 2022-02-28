package com.cis.newpro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cis.newpro.Adapter.adapterClass
import com.cis.newpro.Model.ModelClass
import com.cis.newpro.R
//import com.cis.newpro.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile_page.*
import kotlinx.android.synthetic.main.fragment_retrieve_data.*
import kotlinx.android.synthetic.main.search.*


class ProfilePage : Fragment(), View.OnFocusChangeListener {

    val person = FirebaseFirestore.getInstance().collection("Users")
    var auth = FirebaseAuth.getInstance()
    val arrData = arrayListOf<ModelClass>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_page, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        profileName.onFocusChangeListener = this
        profileEmail.onFocusChangeListener = this
        profileAddress.onFocusChangeListener = this
        profileNumber.onFocusChangeListener = this
        profileBirthDate.onFocusChangeListener = this
        profileUpdate.onFocusChangeListener = this

        reaTimeUpdate()

    }
        private fun reaTimeUpdate() {

           person.addSnapshotListener{QuerySnapshot,FirebaseFirestoreException->
               FirebaseFirestoreException?.let {
                   Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                   return@addSnapshotListener
               }
               QuerySnapshot?.let {
                for (documents in it)
                {
                val data = documents.toObject(ModelClass::class.java)
                arrData.add(data)
                }
                   recyclerView.adapter = adapterClass(arrData)
               }
           }
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {

    }
}

