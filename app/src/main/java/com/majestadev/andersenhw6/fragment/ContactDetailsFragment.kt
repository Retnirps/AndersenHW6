package com.majestadev.andersenhw6.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.majestadev.andersenhw6.R
import com.squareup.picasso.Picasso

private const val CONTACT_ID = "contactId"
private const val CONTACT_NAME = "contactName"
private const val CONTACT_SURNAME = "contactSurname"
private const val CONTACT_PHONE = "contactPhone"
private const val CONTACT_PHOTO = "contactPhoto"

class ContactDetailsFragment : Fragment(R.layout.fragment_contact_details) {
    private val contactId: Int by lazy { requireArguments().getInt(CONTACT_ID, 0) }
    private val contactName: String by lazy { requireArguments().getString(CONTACT_NAME, "") }
    private val contactSurname: String by lazy { requireArguments().getString(CONTACT_SURNAME, "") }
    private val contactPhone: String by lazy { requireArguments().getString(CONTACT_PHONE, "") }
    private val contactPhoto: String by lazy { requireArguments().getString(CONTACT_PHOTO, "") }
    private lateinit var saveChangesButtonClickListener: SaveChangesButtonClickListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contactImageView: ImageView = view.findViewById(R.id.contactImageView)
        val nameEditText: EditText = view.findViewById(R.id.nameEditText)
        val surnameEditText: EditText = view.findViewById(R.id.surnameEditText)
        val phoneEditText: EditText = view.findViewById(R.id.phoneEditText)
        val saveChangesButton: Button = view.findViewById(R.id.saveChangesButton)

        Picasso.get()
            .load(contactPhoto)
            .into(contactImageView)
        nameEditText.setText(contactName)
        surnameEditText.setText(contactSurname)
        phoneEditText.setText(contactPhone)

        saveChangesButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val surname = surnameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            saveChangesButtonClickListener.saveChangesButtonClicked(contactId, name, surname, phone)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SaveChangesButtonClickListener) {
            saveChangesButtonClickListener = context
        } else {
            throw ClassCastException("$context must implement SaveChangesButtonClickListener")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(contactId: Int,
                        contactName: String,
                        contactSurname: String,
                        contactPhone: String,
                        contactPhoto: String) =
            ContactDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(CONTACT_ID, contactId)
                    putString(CONTACT_NAME, contactName)
                    putString(CONTACT_SURNAME, contactSurname)
                    putString(CONTACT_PHONE, contactPhone)
                    putString(CONTACT_PHOTO, contactPhoto)
                }
            }
    }
}