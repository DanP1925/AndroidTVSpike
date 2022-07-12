package com.example.photosearcher.search

import android.content.res.Resources
import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.photosearcher.R
import com.example.photosearcher.data.Photo
import com.example.photosearcher.databinding.ItemPhotoBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class PhotoAdapter(private var dataSet: MutableList<Photo>) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(item: Photo) {
            val r: Resources = binding.root.resources
            val px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                120f,
                r.displayMetrics
            )

            Picasso.get()
                .load(item.urlImage)
                .resize(px.toInt(), px.toInt())
                .centerCrop()
                .placeholder(R.drawable.bg_photo_item)
                .error(R.drawable.bg_photo_item)
                .into(binding.imageviewPhotoBackground, object : Callback {
                    override fun onSuccess() {
                    }

                    override fun onError(e: Exception?) {
                        Picasso.get()
                            .load(item.urlImage)
                            .resize(px.toInt(), px.toInt())
                            .centerCrop()
                            .placeholder(R.drawable.bg_photo_item)
                            .error(R.drawable.bg_photo_item)
                            .into(binding.imageviewPhotoBackground)
                    }
                })

            binding.textviewPhotoTitle.text = item.title
            binding.textviewPhotoDescription.text =
                item.author + " " + item.datePublished?.let { getShortDate(it) }


            binding.root.setOnFocusChangeListener(object : View.OnFocusChangeListener {
                override fun onFocusChange(v: View?, hasFocus: Boolean) {
                    if (hasFocus) {
                        binding.textviewPhotoTitle.typeface = Typeface.DEFAULT_BOLD
                        binding.textviewPhotoDescription.typeface = Typeface.DEFAULT_BOLD
                    } else {
                        binding.textviewPhotoTitle.typeface = Typeface.DEFAULT
                        binding.textviewPhotoDescription.typeface = Typeface.DEFAULT
                    }
                }

            })
        }

        private fun getShortDate(datePublished: String): String {
            val originalFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val targetFormat = SimpleDateFormat("MMM dd yyyy")
            val date: Date = originalFormat.parse(datePublished)
            return targetFormat.format(date)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    fun updateDataSet(dataSet: MutableList<Photo>) {
        this.dataSet = dataSet
    }

}