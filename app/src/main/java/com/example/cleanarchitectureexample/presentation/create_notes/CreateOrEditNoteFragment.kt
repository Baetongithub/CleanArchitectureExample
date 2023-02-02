package com.example.cleanarchitectureexample.presentation.create_notes

import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import com.example.cleanarchitectureexample.databinding.FragmentCreateOrEditNoteBinding
import com.example.cleanarchitectureexample.presentation.base.BaseFragment
import com.example.cleanarchitectureexample.utils.KeyboardHelper

class CreateOrEditNoteFragment :
    BaseFragment<FragmentCreateOrEditNoteBinding>(FragmentCreateOrEditNoteBinding::inflate) {

    override fun initViews() {
        with(vb) {
            imageBack.setOnClickListener { navigateUp() }

            btnSaveNote.setOnClickListener { navigateUp() }

            etCreateNote.setOnEditorActionListener(OnEditorActionListener { _, actionID, _ ->
                if (actionID == EditorInfo.IME_ACTION_DONE) {
                    KeyboardHelper.hideKeyboard(activity)
                    return@OnEditorActionListener true
                }
                return@OnEditorActionListener false
            })
        }
    }

    override fun initViewModel() {

    }

    override fun onResume() {
        super.onResume()
        KeyboardHelper.showKeyboard(context, vb.etCreateNote)
    }
}