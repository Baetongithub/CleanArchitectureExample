package com.example.cleanarchitectureexample.presentation.ui.fragment.create_edit

import android.os.Build
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.viewModels
import com.example.cleanarchitectureexample.R
import com.example.cleanarchitectureexample.databinding.FragmentCreateEditNoteBinding
import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.presentation.ui.base.BaseFragment
import com.example.cleanarchitectureexample.utils.Constants
import com.example.cleanarchitectureexample.utils.KeyboardHelper
import com.example.cleanarchitectureexample.utils.Time
import com.example.cleanarchitectureexample.utils.Toast
import com.example.cleanarchitectureexample.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable
import java.util.*

@AndroidEntryPoint
class CreateEditNoteFragment :
    BaseFragment<FragmentCreateEditNoteBinding>(FragmentCreateEditNoteBinding::inflate) {

    private val viewModel: CreateEditViewModel by viewModels()

    override fun initViews() {

        val note = arguments?.customGetSerializable<Note>(Constants.EDIT_TAG)

        val noteType = arguments?.getString(Constants.EDIT_TYPE)

        with(vb) {
            imageBack.setOnClickListener { navigateUp() }

            etCreateNote.setOnEditorActionListener(OnEditorActionListener { _, actionID, _ ->
                if (actionID == EditorInfo.IME_ACTION_DONE) {
                    KeyboardHelper.hideKeyboard(activity)
                    return@OnEditorActionListener true
                }
                return@OnEditorActionListener false
            })

            //btn save
            if (noteType.equals(Constants.EDIT)) {
                if (note != null) {
                    etCreateNote.setText(note.title)
                    etDescriptionNote.setText(note.desc)
                    btnSaveNote.text = getString(R.string.edit)
                }
            } else {
                btnSaveNote.text = getString(R.string.save)
            }

            btnSaveNote.setOnClickListener {
                editCreateNote(noteType, note)
            }
        }
    }

    override fun initViewModel() {

        viewModel.loading.observe(viewLifecycleOwner) { vb.progressBar.visible = it }

        observeCreateNote()
        observeEditNote()
    }

    private fun editCreateNote(noteType: String?, note: Note?) {
        if (noteType.equals(Constants.EDIT)) {
            if (note != null)
                viewModel.editNote(
                    Note(
                        id = note.id,
                        title = vb.etCreateNote.text.toString() + " (edited)",
                        desc = vb.etDescriptionNote.text.toString(),
                        createdAt = Time.currentTime()
                    )
                )
        } else {
            viewModel.createNotes(
                Note(
                    title = vb.etCreateNote.text.toString(),
                    desc = vb.etDescriptionNote.text.toString(),
                    createdAt = Time.currentTime()
                )
            )
        }
    }

    private fun observeCreateNote() {
        viewModel.createNotesState.collectState(
            onError = { viewModel.loading.postValue(false) },
            onLoading = { viewModel.loading.postValue(true) },
            onSuccess = {
                viewModel.loading.postValue(false)
                navigateUp()
            }
        )
    }

    private fun observeEditNote() {
        viewModel.editNotesState.collectState(
            onError = { Toast.show(context, it) },
            onLoading = { viewModel.loading.postValue(true) },
            onSuccess = {
                navigateUp()
                viewModel.loading.postValue(false)
                Toast.show(context, "$it edited")
            }
        )
    }

    @Suppress("DEPRECATION")
    inline fun <reified T : Serializable> Bundle.customGetSerializable(key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializable(key, T::class.java)
        } else {
            getSerializable(key) as? T
        }
    }

    override fun onResume() {
        super.onResume()
        KeyboardHelper.showKeyboard(context, vb.etCreateNote)
    }
}