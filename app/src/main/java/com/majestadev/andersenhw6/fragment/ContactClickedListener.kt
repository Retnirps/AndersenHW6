package com.majestadev.andersenhw6.fragment

interface ContactClickedListener {
    fun contactClicked(contactId: Int, contactName: String, contactSurname: String, contactPhone: String, contactPhoto: String)
}