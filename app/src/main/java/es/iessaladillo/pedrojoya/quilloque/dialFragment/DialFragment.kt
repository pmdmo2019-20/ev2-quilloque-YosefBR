package es.iessaladillo.pedrojoya.quilloque.dialFragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import es.iessaladillo.pedrojoya.quilloque.R
import kotlinx.android.synthetic.main.dial_fragment.*
import kotlinx.android.synthetic.main.main_activity.*

class DialFragment : Fragment(R.layout.dial_fragment) {

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
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
    }

    private fun erase() {
        lblNumber.text = lblNumber.text.substring(0, lblNumber.text.length-1)
    }

    private fun dialing() {
        lblZero.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblZero.text.toString()
        }
        lblOne.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblOne.text.toString()
        }
        lblTwo.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblTwo.text.toString()
        }
        lblThree.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblThree.text.toString()
        }
        lblFour.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblFour.text.toString()
        }
        lblFive.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblFive.text.toString()
        }
        lblSix.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblSix.text.toString()
        }
        lblSeven.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblSeven.text.toString()
        }
        lblEight.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblEight.text.toString()
        }
        lblNine.setOnClickListener {
            lblNumber.text = lblNumber.text.toString() + lblNine.text.toString()
        }
    }

    private fun navigateToCreateContact() {
        navController.navigate(R.id.contactCreationFragment)
    }
}
