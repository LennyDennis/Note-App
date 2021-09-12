package com.lennydennis.noteapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lennydennis.noteapp.databinding.ItemNoteLayoutBinding
import com.lennydennis.noteapp.models.Note

class NoteAdapter(
    private val noteList: List<Note>
):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    lateinit var noteClickListener: NoteClickListener

    interface NoteClickListener {
        fun onNoteClicked(notePosition: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val noteLayoutBinding = ItemNoteLayoutBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return NoteViewHolder(noteLayoutBinding);
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.noteBinding.noteCard.setOnClickListener{
            noteClickListener.onNoteClicked(position)
        }
        holder.bind(note)
    }

    override fun getItemCount(): Int = noteList.size

    inner class NoteViewHolder(val noteBinding: ItemNoteLayoutBinding) :
        RecyclerView.ViewHolder(noteBinding.root) {
        private val noteTitle: TextView = noteBinding.tvNoteTitle
        private val noteText: TextView = noteBinding.tvNoteText
        private val noteCourse: TextView = noteBinding.tvNoteCourse
        fun bind(note: Note) {
            this.noteTitle.text = note.title
            this.noteText.text = note.text
            this.noteCourse.text = note.course.title
        }
    }

}