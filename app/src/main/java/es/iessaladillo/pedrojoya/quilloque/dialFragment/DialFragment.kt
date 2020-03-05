package es.iessaladillo.pedrojoya.quilloque.dialFragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.SharedViewModel
import es.iessaladillo.pedrojoya.quilloque.SharedViewModelFactory
import es.iessaladillo.pedrojoya.quilloque.contactsFragment.ContactsFragmentAdapter
import es.iessaladillo.pedrojoya.quilloque.data.getCallTypeIcon
import es.iessaladillo.pedrojoya.quilloque.room.Contact
import es.iessaladillo.pedrojoya.quilloque.room.LocalRepository
import es.iessaladillo.pedrojoya.quilloque.room.SuggestedCall
import es.iessaladillo.pedrojoya.quilloque.room.TlfDatabase
import es.iessaladillo.pedrojoya.stroop.invisibleUnless
import kotlinx.android.synthetic.main.contacts_fragment.*
import kotlinx.android.synthetic.main.dial_fragment.*
import kotlinx.android.synthetic.main.main_activity.*

class DialFragment : Fragment(R.layout.dial_fragment) {

    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            LocalRepository(TlfDatabase.getInstance(requireContext()).contactDao, TlfDatabase.getInstance(requireContext()).callDao),
            requireActivity().application
        )
    }

    private val listAdapter: DialFragmentAdapter = DialFragmentAdapter().also {
        it.setOnItemClickListener {position ->
            it.setOnItemClickListener {

            }
        }
    }

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeSuggestions()

    }

    private fun setupViews() {

        lblCreateContact.setOnClickListener{
            navigateToCreateContact()
        }
        dialing()
        imgBackspace.setOnClickListener{
            if (lblNumber.text.isNotEmpty()) {
                erase()
            }
        }
        setupRecyclerView()
        fabCall.setOnClickListener {
            var findContact = sharedViewModel.contacts.value?.find { ct -> ct.phoneNumber == lblNumber.text.toString() }
            var contactId: Long?
            var contactName: String
            var phoneNumber: String
            val date = "07/07/2020"
            val time = "07:07"
            val type = getCallTypeIcon("made")

            if (findContact == null) {
                contactName = "?"
                phoneNumber = lblNumber.text.toString()
                contactId = null
            }
            else {
                contactName = findContact.name
                phoneNumber = findContact.phoneNumber
                contactId = findContact.id
            }

            sharedViewModel.insertRecentCall(contactName, phoneNumber, type, date, time, contactId)
        }
    }

    private fun erase() {
        lblNumber.text = lblNumber.text.substring(0, lblNumber.text.length-1)
        showSuggested()
    }

    private fun dialing() {
        lblZero.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblZero.text.toString()
            showSuggested()
        }
        lblOne.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblOne.text.toString()
            showSuggested()
        }
        lblTwo.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblTwo.text.toString()
            showSuggested()
        }
        lblThree.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblThree.text.toString()
            showSuggested()
        }
        lblFour.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblFour.text.toString()
            showSuggested()
        }
        lblFive.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblFive.text.toString()
            showSuggested()
        }
        lblSix.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblSix.text.toString()
            showSuggested()
        }
        lblSeven.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblSeven.text.toString()
            showSuggested()
        }
        lblEight.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblEight.text.toString()
            showSuggested()
        }
        lblNine.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblNine.text.toString()
            showSuggested()
        }
    }

    private fun showSuggested() {
        if (lblNumber.text == "") {
            sharedViewModel.actualNumber = ""
        }
        else {
            sharedViewModel.actualNumber = "%" + lblNumber.text.toString() + "%"
        }

        sharedViewModel.querySuggested()
    }

    private fun navigateToCreateContact() {
        sharedViewModel.initialAddNumber = lblNumber.text.toString()
        navController.navigate(R.id.contactCreationFragment)
    }

    private fun setupRecyclerView() {
        lstSuggestions.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
    }

    private fun observeSuggestions() {
        sharedViewModel.suggestedCall.observe(this) {
            showSuggestions(it)
        }
    }

    private fun showSuggestions(newList: List<SuggestedCall>) {
        lstSuggestions.post {
            var show: Boolean = newList.isEmpty() && lblNumber.text != ""
            listAdapter.submitList(newList)
            lblCreateContact.invisibleUnless(show)
        }
    }
}
