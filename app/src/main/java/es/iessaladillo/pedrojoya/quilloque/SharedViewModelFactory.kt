package es.iessaladillo.pedrojoya.quilloque

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.quilloque.room.LocalRepository

class SharedViewModelFactory(private val localRepository: LocalRepository,
                             private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SharedViewModel(localRepository, application) as T
    }

}