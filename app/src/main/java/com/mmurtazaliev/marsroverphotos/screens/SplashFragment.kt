package com.mmurtazaliev.marsroverphotos.screens

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.mmurtazaliev.marsroverphotos.R

class SplashFragment : Fragment(R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.postDelayed({
            findNavController().navigate(
                R.id.action_splashFragment_to_mainFragment,
                bundleOf(),
                navOptions {
                    anim {
                        enter = R.anim.nav_default_enter_anim
                        popEnter = R.anim.nav_default_pop_enter_anim
                        popExit = R.anim.nav_default_pop_exit_anim
                        exit = R.anim.nav_default_exit_anim
                    }
                    launchSingleTop = true
                    popUpTo(R.id.app_main_graph) { inclusive = true }
                }
            )
        }, 2500)
    }
}