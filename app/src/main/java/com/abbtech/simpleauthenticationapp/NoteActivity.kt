package com.abbtech.simpleauthenticationapp

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.abbtech.simpleauthenticationapp.databinding.ActivityNoteBinding
import androidx.appcompat.widget.Toolbar
import com.example.data.database.db.DatabaseManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavHostFragment
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DatabaseManager.initDatabase(this)
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                com.example.home.R.id.home_nav_graph,
                com.example.create_new.R.id.create_new_nav_graph,
                com.example.search.R.id.search_nav_graph,
                com.example.event.R.id.event_nav_graph
            )
        )
        val toolbar = findViewById<Toolbar>(R.id.app_bar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigationView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                com.example.home.R.id.homeFragment -> {
                    toolbar.findViewById<TextView>(R.id.toolbar_title)?.text = "Notes"
                    toolbar.findViewById<TextView>(R.id.toolbar_subtitle)?.text = "22 December, 2021"
                    binding.navigationIcon.visibility = View.VISIBLE

                }
                com.example.create_new.R.id.createNewFragment -> {
                    toolbar.findViewById<TextView>(R.id.toolbar_title)?.text = ""
                    toolbar.findViewById<TextView>(R.id.toolbar_subtitle)?.text = ""
                    binding.navigationIcon.visibility = View.GONE
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    toolbar.setNavigationOnClickListener {
                        onBackPressed()
                    }
                }
                com.example.search.R.id.searchFragment -> {
                    toolbar.findViewById<TextView>(R.id.toolbar_title)?.text = "Notes"
                    toolbar.findViewById<TextView>(R.id.toolbar_subtitle)?.text = ""
                    binding.navigationIcon.visibility = View.VISIBLE
                }
                com.example.event.R.id.eventFragment -> {
                    toolbar.findViewById<TextView>(R.id.toolbar_title)?.text = "Events"
                    toolbar.findViewById<TextView>(R.id.toolbar_subtitle)?.text = "22 December, 2021"
                    binding.navigationIcon.visibility = View.VISIBLE
                }
            }
        }
    }
}