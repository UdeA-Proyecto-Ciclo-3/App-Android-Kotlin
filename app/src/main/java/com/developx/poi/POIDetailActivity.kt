package com.developx.poi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class POIDetailActivity : AppCompatActivity() {

    companion object{
        const val NAME_PLACE = "name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi_detail)

        var tvTitlePlace = findViewById<TextView>( R.id.tv_title_place )
        var bundle = intent.extras
        var data = bundle?.getString( NAME_PLACE )
        Log.d("Paquito ", data.toString())
        tvTitlePlace.text = data
    }
}