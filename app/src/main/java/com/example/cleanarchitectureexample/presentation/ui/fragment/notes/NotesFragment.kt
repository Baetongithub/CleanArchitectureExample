package com.example.cleanarchitectureexample.presentation.ui.fragment.notes

import com.example.cleanarchitectureexample.utils.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.cleanarchitectureexample.R
import com.example.cleanarchitectureexample.databinding.FragmentNotesBinding
import com.example.cleanarchitectureexample.presentation.ui.UIState
import com.example.cleanarchitectureexample.presentation.ui.base.BaseFragment
import com.example.cleanarchitectureexample.utils.KeyboardHelper
import com.example.cleanarchitectureexample.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment :
    BaseFragment<FragmentNotesBinding>(FragmentNotesBinding::inflate) {

    private val viewModel: NotesViewModel by viewModels()

    private val notesAdapter = NotesAdapter()

    override fun initViews() {
        vb.fabCreateNote.setOnClickListener { navigate(R.id.to_createNoteFragment) }
        setUpRV()
        viewModel.getNotes()
    }

    override fun initViewModel() {

        viewModel.loading.observe(viewLifecycleOwner) { vb.progressBar.visible = it }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getNotesState.collect {
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
                            notesAdapter.submitList(it.data)
                            Toast.show(context, it.data.toString())
                        }
                    }
                }
            }
        }
    }

    private fun setUpRV() {
        vb.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdapter

            addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        KeyboardHelper.hideKeyboard(activity)
                    }
                }
            })
        }
    }
}