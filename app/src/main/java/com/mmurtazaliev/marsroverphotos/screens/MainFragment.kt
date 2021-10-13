package com.mmurtazaliev.marsroverphotos.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mmurtazaliev.marsroverphotos.R

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigateView = view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController =
            ((childFragmentManager).findFragmentById(R.id.mainFragmentContainer) as NavHostFragment).navController
        bottomNavigateView.setupWithNavController(navController)
    }
}
