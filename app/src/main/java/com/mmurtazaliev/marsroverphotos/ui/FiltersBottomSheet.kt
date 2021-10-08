package com.mmurtazaliev.marsroverphotos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mmurtazaliev.marsroverphotos.R
import java.text.SimpleDateFormat
import java.util.*

class FiltersBottomSheet : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private var selectedRover = ""
    private var selectedData = ""
    lateinit var continueBtn: Button
    lateinit var chooseDataDp: DatePicker

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.filters_bottom_sheet_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val roverSp: Spinner = view.findViewById(R.id.roverSp)
        chooseDataDp = view.findViewById(R.id.chooseDataDp)
        continueBtn = view.findViewById(R.id.continueBtn)
        continueBtn.setOnClickListener { setFilters() }
        ArrayAdapter.createFromResource(
            context ?: view.context,
            R.array.rovers_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            roverSp.adapter = adapter
        }
        roverSp.onItemSelectedListener = this

    }

    private fun setFilters() {
        dismiss()
    }

    private fun setMinDate(rover: String) {
        val date = Date().time
        val pattern = SimpleDateFormat("dd-M-yyyy")
        when (rover) {
            "Curiosity" -> chooseDataDp.minDate = pattern.parse(CURIOSITY_START_DATE).time
            "Opportunity" -> chooseDataDp.minDate = pattern.parse(OPPORTUNITY_START_DATE).time
            "Spirit" -> chooseDataDp.minDate = pattern.parse(SPIRIT_START_DATE).time
        }
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }

    companion object {
        const val TAG = "FiltersBottomSheet"
        const val CURIOSITY_START_DATE = "10-10-2010"
        const val OPPORTUNITY_START_DATE = "11-11-2011"
        const val SPIRIT_START_DATE = "12-12-2012"
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        selectedRover = parent.getItemAtPosition(position).toString()
        Toast.makeText(context, selectedRover, Toast.LENGTH_SHORT).show()
        setMinDate(selectedRover)

    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        selectedRover = parent.getItemAtPosition(0).toString()
        Toast.makeText(context, selectedRover, Toast.LENGTH_SHORT).show()
        setMinDate(selectedRover)
    }
}