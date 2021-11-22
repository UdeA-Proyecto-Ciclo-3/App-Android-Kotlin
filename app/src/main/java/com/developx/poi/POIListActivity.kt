package com.developx.poi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset

class POIListActivity : AppCompatActivity() {

    private val tag: String = "POIListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi_list)

        try {
            val jsonString = getJSONFromAssets()!!                                                  //  Obtiene String JSON del archivo en el directorio assets
            Log.d( tag, jsonString )

        } catch ( e: JSONException) {
            //exception
            e.printStackTrace()
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