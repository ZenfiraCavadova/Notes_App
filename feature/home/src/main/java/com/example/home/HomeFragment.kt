package com.example.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.domain.entities.Note
import com.example.home.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val viewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveData.observe(viewLifecycleOwner){notes->
            displayNotes(notes)
        }
    }

    private fun displayNotes(notes: List<Note>){
        val  notesText=StringBuilder()
        for (note in notes){
            Log.e("HOME","${note.title}: ${note.description}\n")
            notesText.append("${note.title}: ${note.description}\n")
        }
        binding.notesList.text=notesText.toString()
    }

}