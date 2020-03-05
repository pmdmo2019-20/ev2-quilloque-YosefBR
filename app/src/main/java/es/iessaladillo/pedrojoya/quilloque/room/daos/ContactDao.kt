package es.iessaladillo.pedrojoya.quilloque.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import es.iessaladillo.pedrojoya.quilloque.room.Contact
import es.iessaladillo.pedrojoya.quilloque.room.SuggestedCall

@Dao
interface ContactDao {

    @Query("SELECT * FROM CONTACT")
    fun queryAllContacts(): List<Contact>

    @Insert
    fun insertContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact): Int

    @Query("SELECT name AS contactName, phoneNumber AS phoneNumber FROM CONTACT " +
            "WHERE phoneNumber like :phoneNumber " +
            "UNION " +
            "SELECT DISTINCT phoneNumber AS contactName, phoneNumber AS phoneNumber " +
            "FROM Call " +
            "WHERE phoneNumber like :phoneNumber " +
            "AND phoneNumber NOT IN (SELECT phoneNumber FROM Contact)")
    fun suggestContact(phoneNumber: String?): List<SuggestedCall>


    @Query("SELECT * FROM CONTACT WHERE name like :cToSearch")
    fun searchContact(cToSearch: String): List<Contact>
}