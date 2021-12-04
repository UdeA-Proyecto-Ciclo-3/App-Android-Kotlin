package com.developx.poi.models

/** Data Clases: Data Model */
class Places: ArrayList<Place>()

data class Place(
    val name: String,
    val urlImage: String,
    val information: String,
    val description: String,
    val location: String,
    val temperature: Double,
    val coordinates: Coordinates,
)

data class Coordinates (
    val lat: Double,
    val lng: Double
)