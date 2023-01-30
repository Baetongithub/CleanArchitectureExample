package com.example.cleanarchitectureexample.presentation.list_of_notes

import androidx.navigation.fragment.findNavController
import com.example.cleanarchitectureexample.R
import com.example.cleanarchitectureexample.databinding.FragmentListOfNotesBinding
import com.example.cleanarchitectureexample.presentation.base.BaseFragment

class ListOfNotesFragment :
    BaseFragment<FragmentListOfNotesBinding>(FragmentListOfNotesBinding::inflate) {

    override fun initViewModel() {

        vb.fabCreateNote.setOnClickListener { findNavController().navigate(R.id.to_createNoteFragment) }
    }

    override fun initViews() {

    }
}