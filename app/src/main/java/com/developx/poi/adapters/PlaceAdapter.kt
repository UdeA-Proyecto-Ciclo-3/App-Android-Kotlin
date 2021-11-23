package com.developx.poi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.developx.poi.R
import com.developx.poi.models.Place
import com.squareup.picasso.Picasso

class PlaceAdapter( private val context: Context, private val items: ArrayList<Place> ) : RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    /** Infla las vistas de elementos que están diseñadas en el archivo de diseño xml
     *  Crea una nueva {@link ViewHolder} e inicializa algunos campos privados para ser utilizados por RecyclerView. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): ViewHolder {

        return ViewHolder(
            LayoutInflater
                .from( context )
                .inflate(
                    R.layout.layout_item_poi,
                    parent,
                    false
                )
        )
    }

    /** Vincula cada elemento de ArrayList a una vista
     *  Se llama cuando RecyclerView necesita un nuevo {@link ViewHolder} del tipo especificado para representar un elemento..
     *  Este nuevo ViewHolder debe construirse con una nueva Vista que pueda representar los elementos del tipo dado. Puede crear una nueva vista manualmente o inflarla desde un archivo de diseño XML. */
    override fun onBindViewHolder( holder: ViewHolder, position: Int ) {

        val item = items[ position ]

        Picasso.get().load( item.urlImage ).into( holder.ivPlacePreview )
        holder.tvNamePlace.text = item.name
        holder.tvPlaceDescription.text = item.description
        holder.tvPlaceTemperature.text = "${item.temperature.toString()} C"

    }

    /** Obtiene el número de elementos de la lista */
    override fun getItemCount(): Int {
        return items.size
    }

    /** Un ViewHolder describe una vista de elemento y metadatos sobre su lugar dentro de RecyclerView. */
    class ViewHolder( view: View) : RecyclerView.ViewHolder( view ) {
        // Holds the TextView that will add each item to
        val ivPlacePreview: ImageView = view.findViewById( R.id.iv_place_preview )
        val tvNamePlace: TextView = view.findViewById( R.id.tv_name_place )
        val tvPlaceDescription: TextView = view.findViewById( R.id.tv_place_description )
        val tvPlaceTemperature: TextView = view.findViewById( R.id.tv_place_temperature )

    }

}