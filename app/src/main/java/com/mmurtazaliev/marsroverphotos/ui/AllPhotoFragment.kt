package com.mmurtazaliev.marsroverphotos.ui

import android.content.Context
import androidx.fragment.app.Fragment
import com.mmurtazaliev.marsroverphotos.appComponent
import com.mmurtazaliev.marsroverphotos.mainComponent

class AllPhotoFragment:Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.mainComponent?.getPhotoRepository()
    }
}