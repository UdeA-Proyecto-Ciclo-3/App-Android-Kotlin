package com.developx.poi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.developx.poi.interfaces.APIService
import com.developx.poi.models.Places
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class POIListActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "POIListActivity"
        private const val API_URL: String = "http://192.168.0.4:3000/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi_list)
        getPlaces()
    }

    //Configuración de Retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getPlaces(){
        CoroutineScope(Dispatchers.IO).launch {
            val response : Response<Places> = getRetrofit().create(APIService::class.java).getPlaces("places")
            val data= response.body()
            Log.d(TAG,data.toString())
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

}