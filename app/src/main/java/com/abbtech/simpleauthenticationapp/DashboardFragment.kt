package com.abbtech.simpleauthenticationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.abbtech.simpleauthenticationapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    private val arguments by navArgs<DashboardFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fullname = arguments.username
        binding.textWelcome.text = "Welcome $fullname"

    }

    companion object{
        private const val FULLNAME="fullname"
    }
}