package com.cis.newpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.cis.newpro.fragment.ProfilePage
import com.cis.newpro.fragment.RetrieveData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbarlayout.*
import kotlinx.android.synthetic.main.fragment_retrieve_data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toggle: ActionBarDrawerToggle

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomnavbar)

        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav_view.setNavigationItemSelectedListener (this)

        setSupportActionBar(toolBar)

        val homeScreen=HomeScreen()
//        val message=Message()
        val notification=Notification()
        val profilePage=ProfilePage()
        val retriveData = RetrieveData()

        currentPage(HomeScreen())

        bottomNavigationView.setOnNavigationItemSelectedListener {
            onNavigationItemSelected(it)

        }



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        if(toggle.onOptionsItemSelected(item))
        {
           return true
        }
    }


    private fun currentPage( fragment: Fragment) {

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragment).commit()


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.nav_home->{

                currentPage(HomeScreen())
                false

            }
            R.id.nav_message->{

                currentPage(RetrieveData())
                false

            }
            R.id.nav_notification->{

                currentPage(Notification())
                false

            }
            R.id.profilePage->{

                currentPage(ProfilePage())
                false

            }
            else -> true
        }
        return true
    }

//    private fun retrievedata() = CoroutineScope(Dispatchers.IO).launch {
//
//        var querySnapshot = person.get().await()
//        val sb = StringBuilder()
//        for (documents in querySnapshot.documents) {
//
//            sb.append("$person\n")
//        }
//        txtdData.text = sb.toString()
//    }
//
//    val person=documents.toObject
//    <user>()

}