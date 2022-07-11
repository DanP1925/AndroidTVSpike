package com.example.photosearcher.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.photosearcher.R
import com.example.photosearcher.data.Photo
import com.example.photosearcher.databinding.FragmentPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : Fragment() {

    companion object {
        const val NUMBER_OF_COLUMNS = 3
    }

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PhotoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        viewModel.getPhotos()
    }

    private fun setupUi() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding.recyclerviewPhoto) {
        layoutManager =
            StaggeredGridLayoutManager(NUMBER_OF_COLUMNS, StaggeredGridLayoutManager.VERTICAL)
        adapter = PhotoAdapter(emptyList<Photo>().toMutableList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}