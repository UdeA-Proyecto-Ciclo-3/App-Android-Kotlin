package com.developx.poi.models

/** Data Clases: Data Model */
data class Places (
    val list: ArrayList<Place>
)

data class Place(
    val name: String,
    val urlImage: String,
    val information: String,
    val description: String,
    val location: String,
    val temperature: Double,
    val links: Links,
    val coordinates: Coordinates,
)

data class Links (
    val links: ArrayList<String>
)

data class Coordinates (
    val lat: Double,
    val long: Double
)