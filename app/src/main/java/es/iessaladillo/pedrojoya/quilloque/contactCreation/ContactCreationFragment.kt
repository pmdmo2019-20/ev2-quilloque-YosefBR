package es.iessaladillo.pedrojoya.quilloque.contactCreation


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.SharedViewModel
import es.iessaladillo.pedrojoya.quilloque.SharedViewModelFactory
import es.iessaladillo.pedrojoya.quilloque.room.LocalRepository
import es.iessaladillo.pedrojoya.quilloque.room.TlfDatabase
import kotlinx.android.synthetic.main.contact_creation_fragment.*
import kotlinx.android.synthetic.main.main_activity.*

class ContactCreationFragment : Fragment(R.layout.contact_creation_fragment) {

    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            LocalRepository(TlfDatabase.getInstance(requireContext()).contactDao, TlfDatabase.getInstance(requireContext()).callDao),
            requireActivity().application
        )
    }

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        txtPhoneNumber.setText(sharedViewModel.initialAddNumber)
        fabSave.setOnClickListener {
            saveNewContact()
        }
    }

    private fun saveNewContact() {
        sharedViewModel.contactNumber = txtPhoneNumber.text.toString()
        sharedViewModel.insertContact(txtName.text.toString())
        navController.navigateUp()
    }

}
