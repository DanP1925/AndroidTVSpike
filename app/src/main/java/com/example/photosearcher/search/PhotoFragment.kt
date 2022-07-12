package com.example.photosearcher.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.photosearcher.EventObserver
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
        setupObservers()
        viewModel.getPhotos()
    }

    private fun setupUi() {
        setupRecyclerView()
        setupSearchButton()
    }

    private fun setupRecyclerView() = with(binding.recyclerviewPhoto) {
        layoutManager =
            StaggeredGridLayoutManager(NUMBER_OF_COLUMNS, StaggeredGridLayoutManager.VERTICAL)
        adapter = PhotoAdapter(emptyList<Photo>().toMutableList())
    }

    private fun setupSearchButton() {
        binding.buttonPhoto.requestFocus()
    }

    private fun setupObservers() {
        viewModel.dataLoading.observe(viewLifecycleOwner, Observer(::observeDataLoading))
        viewModel.photos.observe(viewLifecycleOwner, EventObserver(::observePhotos))
    }

    private fun observeDataLoading(isLoading: Boolean) {
        binding.progressbarPhoto.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun observePhotos(photos: List<Photo>) {
        (binding.recyclerviewPhoto.adapter as PhotoAdapter).apply {
            updateDataSet(photos.toMutableList())
            notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}