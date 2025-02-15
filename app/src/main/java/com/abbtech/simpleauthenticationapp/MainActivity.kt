package com.abbtech.simpleauthenticationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.abbtech.simpleauthenticationapp.databinding.ActivityMainBinding
import com.example.data.database.db.DatabaseManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavHostFragment
        binding=ActivityMainBinding.inflate(LayoutInflater.from(this))
//        DatabaseManager.initDatabase(this)
        setContentView(binding.root)
    }
    override fun onBackPressed() {
        val navController=findNavController(R.id.fragmentContainerView)
        if (navController.currentDestination?.id ==R.id.dashboardFragment ||
            navController.currentDestination?.id== R.id.loginFragment ||
            navController.currentDestination?.id ==R.id.registerFragment){
            navController.navigate(R.id.welcomeFragment)
        }else{
            super.onBackPressed()
        }
    }
}