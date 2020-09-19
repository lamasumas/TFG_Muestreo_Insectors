package com.example.myapplication.fragments.animals_database.database_recyclerview

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var cv:CardView
    var species:TextView
    var place : TextView
    var country : TextView
    var date : TextView
    var time : TextView
    var lon : TextView
    var lat : TextView
    var idSimple : Long
    var idAdvance : Long
    var humidity: TextView
    var altitude:TextView
    var temperature:TextView
    var pressures:TextView
    var uv: TextView
    var hiddenViews: LinearLayout
    var btnEdit: Button
    var btnDelete : Button
    var generalView:View
    init {
        cv = itemView.findViewById(R.id.cvAnimalScheme)
        species = itemView.findViewById(R.id.tvCvSpecies)
        place = itemView.findViewById(R.id.tvCvPlace)
        country = itemView.findViewById(R.id.tvCvCountry)
        date = itemView.findViewById(R.id.tvCvDate)
        time = itemView.findViewById(R.id.tvCvTime)
        lon = itemView.findViewById(R.id.tvCvLongitude)
        lat = itemView.findViewById(R.id.tvCvLatitude)
        hiddenViews = itemView.findViewById(R.id.hidenCardText)
        btnEdit = itemView.findViewById(R.id.btnEditSightseen)
        btnDelete = itemView.findViewById(R.id.btnDeleteSightseen)
        idSimple = 0
        idAdvance = 0
        pressures = itemView.findViewById(R.id.tvCvPressure)
        altitude = itemView.findViewById(R.id.tvCvAltitude)
        uv = itemView.findViewById(R.id.tvCvUV)
        temperature = itemView.findViewById(R.id.tvCvTemperature)
        humidity = itemView.findViewById(R.id.tvCvHumidity)
        generalView = itemView
    }


}