package com.example.homeworks

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.homeworks.service.WeatherDataSetService
import com.example.homeworks.service.WeatherService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.toolbar_view
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.*
import java.io.IOException

class DetailsActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var service: WeatherService
    private lateinit var toolbar: Toolbar

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initToolbar()
        setSupportActionBar(toolbar);
        service = ApiFactory.weatherService
        val id = intent.extras?.getInt("id") ?: R.string.temp_default_value.toInt()
        launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    service.weatherById(id)
                }
                tv_city?.text = response.name
                val service = WeatherDataSetService()
                if(response.main.temp.toInt() > 0) tv_temp.text = "+" + response.main.temp.toInt().toString()
                else tv_temp.text = response.main.temp.toInt().toString()
                tv_temp.setTextColor(ContextCompat.getColor(this@DetailsActivity,
                    service.setTempColor(response.main.temp)))
                tv_pressure.text = service.getDirection(response.wind.deg) + " " +  response.wind.deg
                tv_humidity.text = response.main.humidity.toString()
                if(response.main.feelsLike.toInt() > 0) tv_feelslike.text = "+" + response.main.feelsLike.toInt().toString()
                else response.main.feelsLike.toInt().toString()
                iv_weather_picture.setImageResource(service.setImage(response.clouds.all))
            } catch (ex: IOException) {
                Log.e("error", ex.message.toString())
            }
        }
    }

    private fun initToolbar() {
        toolbar = toolbar_view as Toolbar
        toolbar.setTitle(R.string.app_name)
        toolbar.inflateMenu(R.menu.toolbar_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        return if(id == R.id.item_search){
            val textInput = search_edit_text.text.toString()
            launch {
                try {
                    val response = withContext(Dispatchers.IO) {
                        service.weatherByName(textInput)
                    }
                    //Toast.makeText(this@MainActivity, response.toString(), Toast.LENGTH_LONG).show()
                    tv_city?.text = response.name
                    tv_temp.text = response.main.toString()
                } catch (ex:IOException){
                    Snackbar.make(findViewById(android.R.id.content), "No such city was found", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
            return true
        } else false
    }

}
