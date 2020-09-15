package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.myapplication.utils.InputValidator
import com.example.myapplication.R
import com.example.myapplication.fragments.abstracts.AbstractDatabaseFragment
import com.example.myapplication.room.DatabaseRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers

class EditSightseen : AbstractDatabaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_avistamiento, container, false)
        view.findViewById<FloatingActionButton>(R.id.btnAñadirAvistamiento).hide()
        view.findViewById<FloatingActionButton>(R.id.btnEditDatabaseAnimal).show()
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idSimple: Long = arguments?.get("idSimple") as Long
        val idAdvance: Long = arguments?.get("idAdvance") as Long
        val dbRepository = DatabaseRepository(view.context)
        val validator = InputValidator()
        setGeneralButtonActions(view, true, idSimple, idAdvance)
        disposables.add(dbRepository.retrieveFullAnimalData(idSimple).observeOn(AndroidSchedulers.mainThread()).subscribe {
            view.findViewById<EditText>(R.id.etEspecie).setText(it.simpleData.especie)
            view.findViewById<EditText>(R.id.etLongitud).setText(it.simpleData.longitude.toString())
            view.findViewById<EditText>(R.id.etLatitud).setText(it.simpleData.latitude.toString())
            val time = it.simpleData.time.split(":")
            val date = it.simpleData.date.split("/")
            view.findViewById<EditText>(R.id.etHour).setText(time.get(0))
            view.findViewById<EditText>(R.id.etMinute).setText(time.get(1))
            view.findViewById<EditText>(R.id.etDay).setText(date.get(0))
            view.findViewById<EditText>(R.id.etMonth).setText(date.get(1))
            view.findViewById<EditText>(R.id.etYear).setText(date.get(2))
            view.findViewById<EditText>(R.id.etHumidity).setText(validator.nullOrEmpty(it.advanceData.humidity.toString()))
            view.findViewById<EditText>(R.id.etPais).setText(validator.nullOrEmpty(it.advanceData.pais))
            view.findViewById<EditText>(R.id.etLugar).setText(validator.nullOrEmpty(it.advanceData.lugar))
            view.findViewById<EditText>(R.id.etTemperature).setText(validator.nullOrEmpty(it.advanceData.temperature.toString()))
            view.findViewById<EditText>(R.id.etIndexUV).setText(validator.nullOrEmpty(it.advanceData.index_uv.toString()))
            view.findViewById<EditText>(R.id.etAltitude).setText(validator.nullOrEmpty(it.advanceData.altitude.toString()))
            view.findViewById<EditText>(R.id.etPressure).setText(validator.nullOrEmpty(it.advanceData.pressure.toString()))
        })


    }


}