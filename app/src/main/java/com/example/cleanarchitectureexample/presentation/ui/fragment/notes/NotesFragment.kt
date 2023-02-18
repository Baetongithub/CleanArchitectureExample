package com.example.cleanarchitectureexample.presentation.ui.fragment.notes

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.cleanarchitectureexample.R
import com.example.cleanarchitectureexample.databinding.FragmentNotesBinding
import com.example.cleanarchitectureexample.databinding.ItemAlertDialogBinding
import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.presentation.ui.base.BaseFragment
import com.example.cleanarchitectureexample.utils.Constants
import com.example.cleanarchitectureexample.utils.Toast
import com.example.cleanarchitectureexample.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment :
    BaseFragment<FragmentNotesBinding>(FragmentNotesBinding::inflate) {

    private val viewModel: NotesViewModel by viewModels()
    private val notesAdapter by lazy { NotesAdapter(this::onLongClickItem) }

    override fun initViews() {
        vb.fabCreateNote.setOnClickListener { navigate(R.id.to_createNoteFragment) }
        viewModel.getNotes()
        setUpRV()
    }

    override fun initViewModel() {
        viewModel.loading.observe(viewLifecycleOwner) { vb.progressBar.visible = it }

        observeGetNotes()
        observeDeleteNote()
    }

    private fun observeGetNotes() {
        viewModel.getNotesState.collectState(
            onError = {
                viewModel.loading.postValue(false)
                Toast.show(context, it)
            },
            onLoading = { viewModel.loading.postValue(true) },
            onSuccess = { data ->
                viewModel.loading.postValue(false)
                notesAdapter.submitList(data)
            }
        )
    }

    private fun observeDeleteNote() {
        viewModel.deleteNotesState.collectState(
            onError = { viewModel.loading.postValue(false) },
            onLoading = { viewModel.loading.postValue(false) },
            onSuccess = {
                Toast.show(context, "$it deleted")
                viewModel.loading.postValue(false)
            }
        )
    }

    private fun setUpRV() {
        vb.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
            adapter = notesAdapter

            addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        vb.fabCreateNote.hide()
                    } else {
                        vb.fabCreateNote.show()
                    }
                }
            })

            // auto scroll to top position
            notesAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    (layoutManager as LinearLayoutManager).scrollToPositionWithOffset(positionStart, 0)
                }
            })
        }
    }

    private fun onLongClickItem(note: Note) {
        val dialogVb = activity?.layoutInflater?.let { ItemAlertDialogBinding.inflate(it) }

        val dialog = context?.let {
            AlertDialog.Builder(it)
                .setView(dialogVb?.root)
                .setNegativeButton(getString(R.string.no), null)
                .setNeutralButton(getString(R.string.edit)) { _, _ ->
                    openEditNote(note)
                }
                .setPositiveButton(getString(R.string.yes)) { _, _ ->
                    viewModel.delete(note)
                }
        }

        if (dialog != null) {
            dialog.create()
            dialog.show()
        }
    }

    private fun openEditNote(note: Note) {
        val bundle = Bundle()
        bundle.putString(Constants.EDIT_TYPE, Constants.EDIT)
        bundle.putSerializable(Constants.EDIT_TAG, note)
        navigate(R.id.to_createNoteFragment, bundle)
    }
}