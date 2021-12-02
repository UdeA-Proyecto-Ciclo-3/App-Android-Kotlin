package com.developx.poi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developx.poi.adapters.PlaceAdapter
import com.developx.poi.models.Places
import com.google.gson.Gson
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset

class POIListActivity : AppCompatActivity() {

    private val tag: String = "POIListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi_list)

        try {
            val jsonString = getJSONFromAssets()!!                              //  Obtiene String JSON del archivo en el directorio assets
            val dataObj = Gson().fromJson( jsonString, Places::class.java )     //  Obtiene Array de usuarios

            Log.d( tag, jsonString )
            Log.d( tag, dataObj.toString() )

            val rvPOIList: RecyclerView = findViewById( R.id.rv_poi_list )       //  Obtiene el View del Activity
            rvPOIList.layoutManager = LinearLayoutManager( this )          //  Configura el LayoutManager que utilizará este RecyclerView, pasandole el contexto.

            val itemAdapter = PlaceAdapter( this, dataObj.places )        // Inicializa el Adapter pasa el contexto y la lista con la data
            rvPOIList.adapter = itemAdapter                                     // Establece en la vista de reciclaje para inflar los elementos.

        } catch ( e: JSONException ) {
            //exception
            e.printStackTrace()
        }

    }

    override fun onCreateOptionsMenu( menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate( R.menu.main_menu, menu )
        return true
    }

    override fun onOptionsItemSelected( item: MenuItem): Boolean {
        // Manejador de opciones del menu principal
        return when ( item.itemId ) {
            R.id.item_preferences -> {
                val intent = Intent(this, PreferencesActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /** Método para cargar el JSON desde el archivo de Activos y devolver el objeto */
    private fun getJSONFromAssets(): String? {

        val json: String?
        val charset: Charset = Charsets.UTF_8

        try {
            val myUsersJSONFile = assets.open("data.json")          //  Abre el archivo
            val size = myUsersJSONFile.available()                          //  Obtiene el tamaño del archivo
            val buffer = ByteArray( size )                                  //  Crea matriz de bytes

            myUsersJSONFile.read( buffer )                                  //  Lee la data del Buffer
            myUsersJSONFile.close()                                         //  Cierra el archivo una ves se termine de leer
            json = String( buffer, charset )                                //  Interpreta caracteres especiales del Charset establecido

        } catch (ex: IOException) {
            ex.printStackTrace()

            return null
        }

        return json
    }

}