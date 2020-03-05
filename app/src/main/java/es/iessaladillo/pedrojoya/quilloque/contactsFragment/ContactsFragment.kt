package es.iessaladillo.pedrojoya.quilloque.contactsFragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
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
import kotlinx.android.synthetic.main.dial_fragment.*
import kotlinx.android.synthetic.main.main_activity.*

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

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeContacts()
    }

    private fun setupViews(){
        sharedViewModel.submitContacts()
        setupRecyclerView()
        emptyView.setOnClickListener {
            navigateToCreateNewContact()
        }
        txtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                sharedViewModel.searchContact("%" + p0.toString() + "%")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun navigateToCreateNewContact() {
        sharedViewModel.initialAddNumber = ""
        navController.navigate(R.id.contactCreationFragment)
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
            showContacts(it)
        }
    }

    private fun showContacts(newList: List<Contact>) {
        lstContacts.post {
            listAdapter.submitList(newList)
            emptyView.invisibleUnless(newList.isEmpty())
        }
    }
}
