package com.cis.newpro.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cis.newpro.Model.ModelClass
import com.cis.newpro.R
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.annotations.NotNull
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_item_layout.view.*
import javax.annotation.RegEx

class adapterClass(private val mUser:List<ModelClass>):RecyclerView.Adapter<adapterClass.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item_layout, parent, false)
        return adapterClass.ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return mUser.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = mUser[position]

        holder.userName.text = user.getUsername()
        holder.fullname.text = user.getFullname()
        Picasso.get().load(user.getProfileImage()).placeholder(R.drawable.ayan).into(holder.profileImage)

    }
        class ViewHolder(@NotNull itemView: View) : RecyclerView.ViewHolder(itemView) {
            var userName: TextView = itemView.findViewById(R.id.txtUsername)
            var fullname: TextView = itemView.findViewById(R.id.txtName)
            var profileImage: ImageView = itemView.findViewById(R.id.imgProfile)
            var follow: Button = itemView.findViewById(R.id.btnFollow)

        }
    }
