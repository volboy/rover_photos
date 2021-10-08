package com.mmurtazaliev.marsroverphotos.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.mmurtazaliev.marsroverphotos.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        const val USER_NAME_KEY = "user_name"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatTextView>(R.id.homeFragmentTitleTv).text = arguments
            ?.getString(USER_NAME_KEY)
    }
}