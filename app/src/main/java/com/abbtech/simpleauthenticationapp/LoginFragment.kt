package com.abbtech.simpleauthenticationapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.abbtech.simpleauthenticationapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val masterKey = MasterKey.Builder(requireContext())
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val sharedPreferences = EncryptedSharedPreferences.create(
            requireContext(),
            SECRET_SHARED_PREF,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        val emailEditText = binding.emailInp
        val pswEditText=binding.pswInp

        emailEditText.setBackgroundResource(R.drawable.edit_text_border)
        pswEditText.setBackgroundResource(R.drawable.edit_text_border)

        emailEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                emailEditText.setBackgroundResource(R.drawable.border_focused)
            } else {
                emailEditText.setBackgroundResource(R.drawable.edit_text_border)
            }
        }

        pswEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                pswEditText.setBackgroundResource(R.drawable.border_focused)
            } else {
                pswEditText.setBackgroundResource(R.drawable.edit_text_border)
            }
        }

        binding.loginBtn.setOnClickListener {
            val enterEmail=binding.emailInp.text.toString()
            val enterPsw=binding.pswInp.text.toString()
            val storedEmail = sharedPreferences.getString(EMAIL, "") ?: ""
            val storedPassword = sharedPreferences.getString(PASSWORD, "") ?: ""
            val storedFullname = sharedPreferences.getString(FULLNAME, "") ?: ""


            if (enterEmail.trim() == storedEmail?.trim() && enterPsw.trim() == storedPassword?.trim()) {
                val intent = Intent(requireActivity(), NoteActivity::class.java)
                startActivity(intent)
            } else {
                showAlert("Invalid email or password")
            }
        }
        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun navigateToDashboard(username:String){
        val navigateAction=LoginFragmentDirections.actionLoginFragmentToDashboardFragment(username)
        findNavController().navigate(navigateAction)
    }
    private fun showAlert(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK"){dialog, _ ->
                dialog.dismiss()
            }.show()
    }
    companion object{
        private const val SECRET_SHARED_PREF = "secret_shared_prefs"
        private const val FULLNAME="fullname"
        private const val EMAIL="email"
        private const val PASSWORD="password"
    }
}
