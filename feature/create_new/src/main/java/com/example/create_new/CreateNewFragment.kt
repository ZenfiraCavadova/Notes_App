package com.example.create_new

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.core.BaseFragment
import com.example.create_new.databinding.FragmentCreateNewBinding


class CreateNewFragment : BaseFragment<FragmentCreateNewBinding,CreateNewViewModel,CreateNewState,CreateNewEffect,CreateNewEvent>() {
//    lateinit var binding: FragmentCreateNewBinding
//    val viewModel by viewModels<CreateNewViewModel>()

    override fun getViewModelClass() = CreateNewViewModel::class.java

    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreateNewBinding = { inflater, viewGroup, value ->
        FragmentCreateNewBinding.inflate(inflater, viewGroup, value)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding=FragmentCreateNewBinding.inflate(inflater,container,false)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val description=binding.description
            description.requestFocus()

        binding.saveBtn.setOnClickListener {
            alertDialog()
//            viewModel.postEvent(
//                CreateNewEvent.SaveNote(
//                    binding.NoteTitle.text.toString(),
//                    binding.description.text.toString()
//                )
//            )
        }

//        binding.saveBtn.setOnClickListener {
//            val title = binding.NoteTitle.text.toString()
//            val description = binding.description.text.toString()
//            viewModel.saveNote(title,description, System.currentTimeMillis())
//
//        }
//        viewModel.liveData.observe(viewLifecycleOwner) { status ->
//
//        }

    }
//    override fun onEffectUpdate(effect: CreateNewEffect) {
//        when (effect) {
//            CreateNewEffect.OnNoteAdded -> alertDialog()
//        }
//    }


    private fun alertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Note Saving")
            .setMessage("Are you sure saving note?")
            .setPositiveButton("Yes") { _, _ ->
                // User clicked "Yes" to save the note
                saveNote()
            }
            .setNegativeButton("No") { _, _ ->
                // User clicked "No"
                Toast.makeText(requireContext(), "Note doesn't added", Toast.LENGTH_LONG).show()
            }
            .show()
    }
    private fun saveNote() {
        val title = binding.NoteTitle.text.toString()
        val description = binding.description.text.toString()
        viewModel.postEvent(CreateNewEvent.SaveNote(title, description))
        Toast.makeText(requireContext(), "Note saved", Toast.LENGTH_LONG).show()
    }
}
