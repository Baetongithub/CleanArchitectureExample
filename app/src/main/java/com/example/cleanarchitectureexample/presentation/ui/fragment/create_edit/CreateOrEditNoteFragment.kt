package com.example.cleanarchitectureexample.presentation.ui.fragment.create_edit

import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cleanarchitectureexample.databinding.FragmentCreateEditNoteBinding
import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.presentation.ui.UIState
import com.example.cleanarchitectureexample.presentation.ui.base.BaseFragment
import com.example.cleanarchitectureexample.utils.KeyboardHelper
import com.example.cleanarchitectureexample.utils.Toast
import com.example.cleanarchitectureexample.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateOrEditNoteFragment :
    BaseFragment<FragmentCreateEditNoteBinding>(FragmentCreateEditNoteBinding::inflate) {

    private val viewModel: CreateEditViewModel by viewModels()

    override fun initViews() {

        with(vb) {
            imageBack.setOnClickListener { navigateUp() }

            btnSaveNote.setOnClickListener {
                observeViewModel(
                    Note(
                        title = vb.etCreateNote.text.toString(),
                        desc = vb.etDescriptionNote.text.toString(),
                        createdAt = System.currentTimeMillis()
                    )
                )
                navigateUp()
            }

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

        viewModel.loading.observe(viewLifecycleOwner) { vb.progressBar.visible = it }

    }

    private fun observeViewModel(note: Note) {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.createNotesState.collect {
                    when (it) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.show(context, it.message)
                        }
                        is UIState.Loading -> {
                            viewModel.loading.postValue(true)
                        }
                        is UIState.Success -> {
                            viewModel.loading.postValue(false)
                            viewModel.createNotes(note)
                        }
                    }
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        KeyboardHelper.showKeyboard(context, vb.etCreateNote)
    }
}