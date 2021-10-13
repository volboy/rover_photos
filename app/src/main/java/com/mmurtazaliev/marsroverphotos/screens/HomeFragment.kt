package com.mmurtazaliev.marsroverphotos.screens

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mmurtazaliev.marsroverphotos.MainViewModel
import com.mmurtazaliev.marsroverphotos.PhotosAdapter
import com.mmurtazaliev.marsroverphotos.R
import com.mmurtazaliev.marsroverphotos.appComponent
import com.mmurtazaliev.marsroverphotos.di.MainComponent
import com.mmurtazaliev.marsroverphotos.displayMetrics
import com.mmurtazaliev.marsroverphotos.mainComponent
import com.mmurtazaliev.marsroverphotos.ui.FiltersBottomSheet
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var mainComponentBuilder: MainComponent.Builder

    private lateinit var mainComponent: MainComponent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireContext().appComponent.injectHomeFragment(this)
        mainComponent = mainComponentBuilder
            .id(0)
            .build()

        val photoRv = view.findViewById<RecyclerView>(R.id.photoRv)
        val adapter = PhotosAdapter(activity?.displayMetrics?.widthPixels ?: 300)
        val flexboxLayoutManager = FlexboxLayoutManager(context)
        flexboxLayoutManager.alignItems = AlignItems.FLEX_START
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START
        photoRv.layoutManager = flexboxLayoutManager
        val viewModelFactory = mainComponent.getViewModelFactory()
        val model = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)

        model.photos.observe(viewLifecycleOwner) {
            adapter.setItems(it)
            photoRv.adapter = adapter
            Toast.makeText(context, "Size=${it.size}", Toast.LENGTH_LONG).show()
        }

        model.errors.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, "Error = $error", Toast.LENGTH_LONG).show()
        }
    }
}