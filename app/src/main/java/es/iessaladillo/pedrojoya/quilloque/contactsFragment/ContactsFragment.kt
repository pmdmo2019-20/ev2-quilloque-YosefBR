package es.iessaladillo.pedrojoya.quilloque.contactsFragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.SharedViewModel
import es.iessaladillo.pedrojoya.quilloque.SharedViewModelFactory
import es.iessaladillo.pedrojoya.quilloque.room.Contact
import es.iessaladillo.pedrojoya.quilloque.room.LocalRepository
import es.iessaladillo.pedrojoya.quilloque.room.TlfDatabase
import es.iessaladillo.pedrojoya.stroop.invisibleUnless
import kotlinx.android.synthetic.main.contacts_fragment.*
import kotlinx.android.synthetic.main.contacts_fragment_item.*

class ContactsFragment : Fragment(R.layout.contacts_fragment) {

    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            LocalRepository(TlfDatabase.getInstance(requireContext()).contactDao, TlfDatabase.getInstance(requireContext()).callDao),
            requireActivity().application
        )
    }

    private val listAdapter: ContactsFragmentAdapter = ContactsFragmentAdapter().also {
        it.setOnItemClickListener {position ->
            it.setOnItemClickListener {

            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeContacts()
    }

    private fun setupViews(){
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lstContacts.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
    }

    private fun observeContacts() {
        sharedViewModel.contacts.observe(this) {
            showPlayers(it)
        }
    }

    private fun showPlayers(newList: List<Contact>) {
        lstContacts.post {
            listAdapter.submitList(newList)
            emptyView.invisibleUnless(newList.isEmpty())
        }
    }
}
