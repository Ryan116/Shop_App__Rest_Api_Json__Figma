package com.example.shopapp.features.bookmarksScreen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopapp.features.bookmarksScreen.R
import com.example.shopapp.features.bookmarksScreen.databinding.FragmentBookmarksBinding
import com.example.shopapp.features.bookmarksScreen.presentation.adapter.BookmarkAdapter
import com.example.shopapp.features.bookmarksScreen.presentation.viewModel.BookmarkViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment() {

    private var _binding: FragmentBookmarksBinding? = null
    private val binding
        get() = _binding!!
    private val bookmarkViewModel by viewModel<BookmarkViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewAdapter()
        setupFloatingButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerViewAdapter() {
        val adapterBookmark = BookmarkAdapter(
            {
                findNavController().navigate(R.id.action_to_productDetailsFragment)

            }, { phoneBookmark ->
                bookmarkViewModel.deleteBookmark(phoneBookmark)
            }
        )
        binding.recyclerViewBookmarks.apply {
            adapter = adapterBookmark
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        bookmarkViewModel.bookmarksList.observe(viewLifecycleOwner) {
            adapterBookmark.submitList(it)
        }
    }

    private fun setupFloatingButton() {
        binding.buttonDeleteAll.setOnClickListener {
            bookmarkViewModel.deleteAllBooks()
        }
    }
}