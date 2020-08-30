package com.research.aac.view


import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


class BindingAdapterUtil {
    companion object {
//        @BindingAdapter("android:src")
//        @JvmStatic
//        fun setImageViewResource(imageView: AppCompatImageView, resource : Int) {
//            imageView.setImageResource(resource)
//        }

        @BindingAdapter("dataSource")
        @JvmStatic
        fun <T> setRecyclerViewDataSource(recyclerView: RecyclerView, data: T) {
            if(data == null) {
                return
            }
            if (recyclerView.adapter is BindableAdapter<*>) {
                (recyclerView.adapter as BindableAdapter<T>).setData(data)
            }
        }
    }
    interface BindableAdapter<T> {
        fun setData(data : T)
    }
}