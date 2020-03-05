package es.iessaladillo.pedrojoya.quilloque

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.quilloque.room.Contact
import es.iessaladillo.pedrojoya.quilloque.room.Repository

class SharedViewModel(private val repository: Repository,
                      private val application: Application) : ViewModel() {

    val contacts: LiveData<List<Contact>> = repository.queryAllContacts()
    var contactNumber: String = ""

    fun insertContact(name: String) {
        Thread {
            try {
                repository.insertContact(Contact(0, name, contactNumber))
            } catch (e: Exception) {

            }
        }.start()
    }

}