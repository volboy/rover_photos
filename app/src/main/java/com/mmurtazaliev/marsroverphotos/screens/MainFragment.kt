package com.mmurtazaliev.marsroverphotos.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mmurtazaliev.marsroverphotos.R

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.postDelayed(
            { findNavController().navigate(R.id.action_mainFragment_to_homeFragment) },
            2500
        )
    }
}
