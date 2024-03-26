package com.abbtech.simpleauthenticationapp

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.abbtech.simpleauthenticationapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRegisterBinding.inflate(inflater,container,false)
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

        val nameEditText=binding.fullname
        val emailEditText=binding.email
        val pswEditText=binding.password
        val rePswEditText=binding.repassword

        nameEditText.setBackgroundResource(R.drawable.edit_text_border)
        emailEditText.setBackgroundResource(R.drawable.edit_text_border)
        pswEditText.setBackgroundResource(R.drawable.edit_text_border)
        rePswEditText.setBackgroundResource(R.drawable.edit_text_border)

        nameEditText.setOnFocusChangeListener{_,hasFocus ->
            setBorder(nameEditText,hasFocus)
        }
        emailEditText.setOnFocusChangeListener { _, hasFocus ->
            setBorder(emailEditText,hasFocus)
        }
        pswEditText.setOnFocusChangeListener{_, hasFocus->
            setBorder(pswEditText,hasFocus)
        }
        rePswEditText.setOnFocusChangeListener{_, hasFocus->
            setBorder(rePswEditText,hasFocus)
        }

        binding.registerBtn.setOnClickListener {
            val fullname=binding.fullname.text.toString()
            val email=binding.email.text.toString()
            val psw=binding.password.text.toString()
            val repsw=binding.repassword.text.toString()

            if (email.isEmpty() || psw.isEmpty() || fullname.isEmpty()) {
                showAlert("Each input is required.")
            } else if (!isValidEmail(email)) {
                showAlert("Please enter a valid email address.")
            } else if (!isPasswordValid(psw)) {
                showAlert("Password must contain at least 7 characters, including at least one number and one uppercase letter.")
            } else if (psw != repsw) {
                showAlert("Passwords do not match.")
            } else if (isEmailRegistered(email)) {
                showAlert("This email is already registered.")
            } else {
                with(sharedPreferences.edit()){
                    putString(FULLNAME, fullname)
                    putString(EMAIL, email)
                    putString(PASSWORD, psw)
                    putBoolean(getEmailRegisteredKey(email), true)
                    putBoolean("has_registered", true)
                    putBoolean("isRegistrationSuccessful", true)
                    apply()
                }
                startActivity(Intent(requireContext(), NoteActivity::class.java))
                requireActivity().finish()
            }
        }

        binding.signIn.setOnClickListener {
            checkAndNavigateToLoginScreen()
        }

    }
    private fun setBorder(editText: EditText, hasFocus:Boolean){
        val backgroundResource=if (hasFocus) R.drawable.border_focused else R.drawable.edit_text_border
        editText.setBackgroundResource(backgroundResource)
    }
    private fun checkAndNavigateToLoginScreen() {
        val hasUserData =
            sharedPreferences.contains(FULLNAME) && sharedPreferences.contains(EMAIL) && sharedPreferences.contains(
                PASSWORD
            )

        if (hasUserData) {
            navigateToLoginFragment()
        } else {
            showAlert("No user data found. Please register first.")
        }
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK"){ dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun saveUserData(fullname: String, email: String, psw: String) {
        if (!isFullNameValid(fullname)) {
            showAlert("Please enter a valid full name with only letters and two words.")
            return
        }
        if (isEmailRegistered(email)) {
            showAlert("This email is already registered.")
        } else {
            with(sharedPreferences.edit()){
                putString(FULLNAME, fullname)
                putString(EMAIL, email)
                putString(PASSWORD, psw)
                putBoolean(getEmailRegisteredKey(email), true)
                apply()
            }
            navigateToDashboardFragment()
        }
    }

    private fun navigateToLoginFragment(){
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }
    private fun navigateToDashboardFragment(){
        val username:String=binding.fullname.text.toString()
        val navigateAction=RegisterFragmentDirections.actionRegisterFragmentToDashboardFragment(username)
        findNavController().navigate(navigateAction)
    }
    private fun isValidEmail(email: String): Boolean {
        val emailParts = email.split("@")
        return emailParts.size == 2 && emailParts[0].isNotEmpty() && emailParts[1].contains(".")
    }
    private fun isPasswordValid(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[0-9])(?=.*[A-Z]).{7,}\$")
        return password.matches(passwordRegex)
    }
    private fun isFullNameValid(fullname: String):Boolean{
        val words = fullname.trim().split("\\s+".toRegex())
        return words.size == 2 && words.all { it.matches(Regex("^[a-zA-Z]+$")) }
    }
    private fun isEmailRegistered(email: String): Boolean {
        return sharedPreferences.getBoolean(getEmailRegisteredKey(email), false)
    }

    private fun getEmailRegisteredKey(email: String): String {
        return "registered_email_$email"
    }

    companion object{
        private const val SECRET_SHARED_PREF = "secret_shared_prefs"
        private const val FULLNAME="fullname"
        private const val EMAIL="email"
        private const val PASSWORD="password"
    }
}