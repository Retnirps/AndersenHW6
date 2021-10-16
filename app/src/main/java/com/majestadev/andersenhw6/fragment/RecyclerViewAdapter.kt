package com.majestadev.andersenhw6.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.majestadev.andersenhw6.R
import com.majestadev.andersenhw6.model.Contact
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val onClick: (Contact) -> Unit): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var contactsList = emptyList<Contact>()

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(contact: Contact) {
            val contactPhotoImageView:ImageView = view.findViewById(R.id.contactPhotoImageView)
            val contactNameTextView: TextView = view.findViewById(R.id.contactNameTextView)

            Picasso.get()
                .load(contact.photo)
                .into(contactPhotoImageView)
            contactNameTextView.text = contact.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactsList[position]
        holder.bind(contact)
        holder.itemView.setOnClickListener { onClick(contact) }
    }

    override fun getItemCount(): Int = contactsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(contactList: List<Contact>) {
        this.contactsList = contactList
        notifyDataSetChanged()
    }
}