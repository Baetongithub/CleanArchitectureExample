package com.example.cleanarchitectureexample.presentation.ui.fragment.notes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cleanarchitectureexample.databinding.ItemNotesBinding
import com.example.cleanarchitectureexample.domain.model.Note

class NotesAdapter : ListAdapter<Note, NotesAdapter.NotesViewHolder>(NoteDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NotesViewHolder(private val itemBinding: ItemNotesBinding) :
        ViewHolder(itemBinding.root) {
        fun bind(note: Note) {
            itemBinding.tvNoteTitle.text = note.title
            itemBinding.tvNoteDesc.text = note.desc
            itemBinding.tvNoteTime.text = note.createdAt.toString()
        }
    }

    private class NoteDiffUtil : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }
}