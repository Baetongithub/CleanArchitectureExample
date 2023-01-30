package com.example.cleanarchitectureexample.presentation.create_notes

import androidx.navigation.fragment.findNavController
import com.example.cleanarchitectureexample.databinding.FragmentCreateOrEditNoteBinding
import com.example.cleanarchitectureexample.presentation.base.BaseFragment

class CreateOrEditNoteFragment :
    BaseFragment<FragmentCreateOrEditNoteBinding>(FragmentCreateOrEditNoteBinding::inflate) {

    override fun initViewModel() {

        vb.imageBack.setOnClickListener { findNavController().navigateUp() }
    }

    override fun initViews() {

    }
}