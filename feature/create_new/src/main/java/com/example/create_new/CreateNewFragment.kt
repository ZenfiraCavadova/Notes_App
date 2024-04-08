package com.example.create_new

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.create_new.databinding.FragmentCreateNewBinding


class CreateNewFragment : Fragment() {
    lateinit var binding: FragmentCreateNewBinding
    val viewModel by viewModels<CreateNewViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCreateNewBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val description=binding.description
            description.requestFocus()

        binding.saveBtn.setOnClickListener {
            val title = binding.NoteTitle.text.toString()
            val description = binding.description.text.toString()
            viewModel.saveNote(title,description, System.currentTimeMillis())

        }
        viewModel.liveData.observe(viewLifecycleOwner) { status ->

        }

    }



}