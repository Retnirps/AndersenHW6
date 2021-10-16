package com.majestadev.andersenhw6.fragment

interface SaveChangesButtonClickListener {
    fun saveChangesButtonClicked(contactId: Int, contactName: String, contactSurname: String, contactPhone: String)
}