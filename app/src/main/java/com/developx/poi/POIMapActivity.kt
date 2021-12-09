package com.developx.poi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.developx.poi.databinding.ActivityPoiMapBinding

class POIMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityPoiMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityPoiMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        var bundle = intent.extras
        var latitude =  bundle?.getDouble(POIDetailActivity.PLACE_LATITUDE)
        var longitude =  bundle?.getDouble(POIDetailActivity.PLACE_LONGITUDE)
        var name =  bundle?.getString(POIDetailActivity.NAME_PLACE)
        mMap = googleMap
        this.setTitle(name)

        val place = LatLng(latitude!!, longitude!!)
        mMap.addMarker(MarkerOptions().position(place).title(name))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place))
    }
}