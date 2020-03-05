package es.iessaladillo.pedrojoya.quilloque

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.quilloque.room.*
import kotlin.concurrent.thread

class SharedViewModel(private val repository: Repository,
                      private val application: Application) : ViewModel() {


    var contacts: MutableLiveData<List<Contact>> = MutableLiveData()

    var actualNumber: String = ""

    val suggestedCall: MutableLiveData<List<SuggestedCall>> = MutableLiveData()

    val recentCalls: LiveData<List<RecentCalls>> = repository.queryRecentCalls(10)

    var contactNumber: String = ""

    var initialAddNumber: String = ""

    fun insertContact(name: String) {
        Thread {
            try {
                repository.insertContact(Contact(0, name, contactNumber))
            } catch (e: Exception) {

            }
        }.start()
    }

    fun insertRecentCall(contactName: String, phoneNumber: String, type: Int, date: String, time: String, contactId: Long?) {
        Thread {
            try {
                repository.insertCall(Call(0, contactName, phoneNumber, type, date, time, contactId))
            } catch (e: Exception) {

            }
        }.start()
    }

    fun querySuggested() {
        Thread {
            try {
                suggestedCall.postValue(repository.suggestContact(actualNumber))
            } catch (e: Exception) {

            }
        }.start()
    }

    fun searchContact(cToSearch: String) {
        Thread {
            try {
                contacts.postValue(repository.searchContact(cToSearch))
            } catch (e: Exception) {

            }
        }.start()
    }

    fun submitContacts() {
        Thread {
            try {
                contacts.postValue(repository.queryAllContacts())
            } catch (e: Exception) {

            }
        }.start()
    }

}