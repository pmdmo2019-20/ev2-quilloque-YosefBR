package es.iessaladillo.pedrojoya.quilloque

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val appBarConfiguration: AppBarConfiguration =
        AppBarConfiguration(
            setOf(R.id.dialDestination, R.id.recentDestination,
                R.id.contactDestination)
        )

    private val navController: NavController by lazy {
        findNavController(R.id.navHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupViews()
    }

    private fun setupViews() {
        bottomNavigationView.setupWithNavController(navController)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            navigateToOption(it)
            true
        }
    }

    private fun navigateToOption(item: MenuItem) {
        when (item.itemId) {
            R.id.dialDestination -> navigateToFavorites()
            R.id.recentDestination -> navigateToCalendar()
            R.id.contactDestination -> navigateToMusic()
        }
    }

    private fun navigateToFavorites() {
        navController.navigate(R.id.dialFragment)
    }

    private fun navigateToCalendar() {
        navController.navigate(R.id.recentFragment)
    }

    private fun navigateToMusic() {
        navController.navigate(R.id.contactsFragment)
    }

}
