package com.mmurtazaliev.marsroverphotos

import android.app.Activity
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mmurtazaliev.marsroverphotos.api.NasaApi
import com.mmurtazaliev.marsroverphotos.repository.PhotoRepository
import com.mmurtazaliev.marsroverphotos.viewmodel.DatabaseHelper
import com.mmurtazaliev.marsroverphotos.viewmodel.NetworkUtils
import com.mmurtazaliev.marsroverphotos.viewmodel.ViewModelFactory
import javax.inject.Inject
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    @Inject
    lateinit var networkUtils: NetworkUtils

    @Inject
    lateinit var photoRepository: PhotoRepository


    private val flexboxLayoutManager = FlexboxLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val databaseHelper = appComponent.getDatabaseHelper()
        //val networkUtils = appComponent.getNetworkUtils()
        appComponent.getMainComponent().injectMainActivity(this)
        val adapter = PhotosAdapter(displayMetrics.widthPixels)
        val photoRV: RecyclerView = findViewById(R.id.photoRV)
        flexboxLayoutManager.alignItems = AlignItems.FLEX_START
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START
        photoRV.layoutManager = flexboxLayoutManager
        val viewModelFactory = ViewModelFactory(photoRepository)
        val model = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)

        model.photos.observe(this) {
            adapter.setItems(it)
            photoRV.adapter = adapter
            Toast.makeText(this, "Size=${it.size}", Toast.LENGTH_LONG).show()
        }

        model.errors.observe(this) { error ->
            Toast.makeText(this, "Error = $error", Toast.LENGTH_LONG).show()
        }
    }
}

val Activity.displayMetrics: DisplayMetrics
    get() {
        val displayMetrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= 30) {
            display?.apply {
                getRealMetrics(displayMetrics)
            }
        } else {
            windowManager.defaultDisplay.getRealMetrics(displayMetrics)
        }
        return displayMetrics
    }

val Activity.screenSizeInDp: Point
    get() {
        val point = Point()
        displayMetrics.apply {
            point.x = (widthPixels / density).roundToInt()
            point.y = (heightPixels / density).roundToInt()
        }
        return point
    }