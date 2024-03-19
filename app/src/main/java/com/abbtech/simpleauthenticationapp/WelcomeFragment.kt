package com.abbtech.simpleauthenticationapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abbtech.simpleauthenticationapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    lateinit var binding: FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val isRegistered = sharedPreferences.getBoolean("has_registered", false)
        Log.e("WelcomeFragment", "isRegistered: $isRegistered")

        binding.getStarted.setOnClickListener {
            if (!isRegistered) {
                navigateToRegister()
                sharedPreferences.edit().putBoolean("has_registered", true).apply()
            } else {
                navigateToLogin()
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
}