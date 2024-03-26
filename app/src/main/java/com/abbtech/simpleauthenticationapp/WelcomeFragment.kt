package com.abbtech.simpleauthenticationapp

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.abbtech.simpleauthenticationapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    lateinit var binding: FragmentWelcomeBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val masterKey = MasterKey.Builder(requireContext())
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        sharedPreferences = EncryptedSharedPreferences.create(
            requireContext(),
            SECRET_SHARED_PREF,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        val isRegistered = sharedPreferences.getBoolean("has_registered", false)
        val isRegistrationSuccessful = sharedPreferences.getBoolean("isRegistrationSuccessful", false)

        binding.getStarted.setOnClickListener {
            if (isRegistered && isRegistrationSuccessful) {
                navigateToLogin()
            } else {
                navigateToRegister()
            }
        }
    }
    private fun navigateToRegister() {
        val navigationAction = WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
        findNavController().navigate(navigationAction)
    }

    private fun navigateToLogin() {
        val navigationAction = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
        findNavController().navigate(navigationAction)
    }
    companion object{
        private const val SECRET_SHARED_PREF = "secret_shared_prefs"
    }
}