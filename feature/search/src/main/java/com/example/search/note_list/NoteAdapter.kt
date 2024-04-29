package com.example.home.notes_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.entities.Note
import com.example.search.databinding.NoteItemBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteAdapter : ListAdapter<Note,NoteAdapter.NotesViewHolder>(NoteDiffCallback()) {
    private var allNotes: List<Note> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view= NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val item =getItem(position)
        holder.bindData(item)
    }
    inner class NotesViewHolder(private val notesItemBinding: NoteItemBinding):  ViewHolder(notesItemBinding.root) {
        fun bindData(note: Note){
            notesItemBinding.noteTitle.text=note.title
            notesItemBinding.noteDescription.text=note.description
            notesItemBinding.date.text=formatDate(note.creationDate)
        }
        private fun formatDate(timestamp: Long): String {
            val sdf = SimpleDateFormat("MMM dd, yyyy 'at' HH:mm", Locale.getDefault())
            return sdf.format(Date(timestamp))
        }
    }

    private class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.title == newItem.title && oldItem.description == newItem.description
        }
    }

    fun submitAllNotes(notes: List<Note>) {
        allNotes = notes
        submitList(notes)
    }

    // Filter function to filter notes based on title or subtitle
    fun filterNotes(query: String) {
        val filteredNotes = allNotes.filter { note ->
            note.title.contains(query, ignoreCase = true) || note.description.contains(query, ignoreCase = true)
        }
        submitList(filteredNotes)
    }

}