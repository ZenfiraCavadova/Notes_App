package com.example.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.search.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    lateinit var  binding:FragmentSearchBinding
    val viewModel by viewModels<SearchViewModel> ()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }
}