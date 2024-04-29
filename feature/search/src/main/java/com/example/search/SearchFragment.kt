package com.example.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.example.core.BaseFragment
import com.example.home.notes_list.NoteAdapter
import com.example.search.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel, SearchState, SearchEffect, SearchEvent>(),SearchView.OnQueryTextListener  {
//    lateinit var  binding:FragmentSearchBinding
//    val viewModel by viewModels<SearchViewModel> ()
    private lateinit var adapter: NoteAdapter
    override fun getViewModelClass() = SearchViewModel::class.java

    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding = { inflater, viewGroup, value ->
        FragmentSearchBinding.inflate(inflater, viewGroup, value)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=NoteAdapter()
        binding.notesList.adapter=adapter

        binding.searchInp.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        binding.searchInp.clearFocus()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { query ->
            adapter.filterNotes(query)
        }
        return true
    }

    override fun onStateUpdate(state: SearchState) {
        state.notes?.let { list ->
            adapter.submitAllNotes(list)
        }
    }
}