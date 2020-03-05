package es.iessaladillo.pedrojoya.quilloque.room

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.quilloque.room.daos.CallDao
import es.iessaladillo.pedrojoya.quilloque.room.daos.ContactDao

class LocalRepository(private val contactDao: ContactDao, private val callDao: CallDao) : Repository {

    //CALL
    override fun queryRecentCalls(limit: Int): LiveData<List<RecentCalls>> = callDao.queryRecentCalls(limit)

    override fun insertCall(call: Call) = callDao.insertCall(call)

    override fun deleteCall(call: Call): Int = callDao.deleteCall(call)


    //CONTACT
    override fun searchContact(cToSearch: String): List<Contact> = contactDao.searchContact(cToSearch)

    override fun queryAllContacts(): List<Contact> = contactDao.queryAllContacts()

    override fun insertContact(contact: Contact) = contactDao.insertContact(contact)

    override fun deleteContact(contact: Contact): Int = contactDao.deleteContact(contact)

    override fun suggestContact(phoneNumber: String?): List<SuggestedCall> = contactDao.suggestContact(phoneNumber)


}