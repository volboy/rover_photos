package com.mmurtazaliev.marsroverphotos

import android.app.Activity
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mmurtazaliev.marsroverphotos.di.MainComponent
import com.mmurtazaliev.marsroverphotos.viewmodel.ViewModelFactory
import javax.inject.Inject
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainComponentBuilder: MainComponent.Builder


    private val flexboxLayoutManager = FlexboxLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.injectMainActivity(this)
        val mainComponent = mainComponentBuilder
            .id(0)
            .build()
        val adapter = PhotosAdapter(displayMetrics.widthPixels)
        val photoRV: RecyclerView = findViewById(R.id.photoRV)
        flexboxLayoutManager.alignItems = AlignItems.FLEX_START
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START
        photoRV.layoutManager = flexboxLayoutManager
        val viewModelFactory = ViewModelFactory(mainComponent.getPhotoRepository())
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

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
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