package es.iessaladillo.pedrojoya.quilloque.room

import androidx.lifecycle.LiveData

interface Repository {

    //CONTACT
    fun queryAllContacts(): List<Contact>
    fun insertContact(contact: Contact)
    fun deleteContact(contact: Contact): Int
    fun suggestContact(phoneNumber: String?): List<SuggestedCall>
    fun searchContact(cToSearch: String): List<Contact>

    //CALL
    fun queryRecentCalls(limit: Int): LiveData<List<RecentCalls>>
    fun insertCall(call: Call)
    fun deleteCall(call: Call): Int
}