package es.iessaladillo.pedrojoya.quilloque.room

import androidx.lifecycle.LiveData

interface Repository {

    //CONTACT
    fun queryAllContacts(): LiveData<List<Contact>>
    fun insertContact(contact: Contact)
    fun deleteContact(contact: Contact): Int
    fun suggestContact(phoneNumber: String): LiveData<List<SuggestedCall>>

    //CALL
    fun queryRecentCalls(limit: Int): LiveData<List<RecentCalls>>
    fun insertCall(call: Call)
    fun deleteCall(call: Call): Int
}