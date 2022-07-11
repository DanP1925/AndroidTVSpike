package com.example.photosearcher.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.photosearcher.R
import com.example.photosearcher.data.Photo
import com.example.photosearcher.databinding.FragmentPhotoBinding

class PhotoFragment : Fragment() {

    companion object {
        const val NUMBER_OF_COLUMNS = 3
    }

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!

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