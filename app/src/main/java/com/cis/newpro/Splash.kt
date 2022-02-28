package com.cis.newpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.cis.newpro.fragment.Login
import com.cis.newpro.fragment.ProfilePage
import com.cis.newpro.fragment.RetrieveData
import com.cis.newpro.fragment.SignUp
import com.google.firebase.auth.FirebaseAuth

class Splash : AppCompatActivity() {

    var handler = Handler()

    val auth = FirebaseAuth.getInstance()

    lateinit var splashIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashIcon = findViewById(R.id.splashIcon)

        handler.postDelayed(Runnable {

            splashIcon.visibility = View.GONE

            checkUserExist()

        }, 3000)


    }


    private fun checkUserExist() {

        if (auth.currentUser == null) {

            val login=Login()
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, login)
                .commit()


        } else {
            Toast.makeText(this, "  Your Are Already Logged In", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}