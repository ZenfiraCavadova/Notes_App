package com.example.home

import NotesAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.core.BaseFragment
import com.example.domain.entities.Note
import com.example.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel, HomeState, HomeEffect, HomeEvent>() {
//    lateinit var binding: FragmentHomeBinding
//    val viewModel by viewModels<HomeViewModel>()
    private lateinit var adapter: NotesAdapter
    override fun getViewModelClass() = HomeViewModel::class.java

    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding = { inflater, viewGroup, value ->
        FragmentHomeBinding.inflate(inflater, viewGroup, value)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding=FragmentHomeBinding.inflate(inflater,container,false)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=NotesAdapter()
        binding.notesList.adapter=adapter


//        viewModel.liveData.observe(viewLifecycleOwner){notes->
////            displayNotes(notes)
//            adapter.submitList(notes)
//        }
    }
    override fun onStateUpdate(state: HomeState) {
        state.notes?.let { list ->
            adapter.submitList(list)
        }
    }

//        private fun displayNotes(notes: List<Note>){
//            adapter.submitList(notes)
//        val  notesText=StringBuilder()
//        for (note in notes){
//            Log.e("HOME","${note.title}: ${note.description}\n")
//            notesText.append("${note.title}: ${note.description}\n")
//        }
//        binding.notesList.text=notesText.toString()
    }