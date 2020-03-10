package com.example.homeworks

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworks.responce.Weather
import com.example.homeworks.service.WeatherService
import com.example.homeworks.weather.WeatherAdapter
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope(){

    private lateinit var service: WeatherService
    private lateinit var toolbar: Toolbar
    private var lat: Double = 0.0
    private var lon: Double = 0.0
    private var weatherAdapter: WeatherAdapter? = null
    private val requestCode: Int = 1

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list: ArrayList<Weather> = ArrayList()
        weatherAdapter = WeatherAdapter(list){
                weather -> navigateToDetails(weather)
        }
        rv_weather.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_weather.adapter = weatherAdapter
        initToolbar()
        setSupportActionBar(toolbar);
        service = ApiFactory.weatherService
        checkPermission()
    }

    private fun checkPermission(){
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) requestPermissionWithRationale()
        else setLocation()
    }

    private fun getCities(){
        launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    val count = 10
                    service.weatherOfCitiesInCycle(lat, lon, count)
                }
                weatherAdapter?.dataSource = response.list
                weatherAdapter?.notifyDataSetChanged()
            } catch (ex:IOException){
                Snackbar.make(findViewById(android.R.id.content), "No such city was found", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setLocation(){
        val mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient
            .lastLocation
            .addOnSuccessListener(this) {
                if (it != null) {
                    lon = it.longitude
                    lat = it.latitude
                }
                getCities()
            }
    }

    private fun requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
            && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)){
            val message = getString(R.string.snack_message)
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction(
                    getString(R.string.grant).toUpperCase(Locale.ROOT)
                ) { requestPerms() }
                .show()
        } else requestPerms()
    }

    private fun requestPerms() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissions =
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            this.requestPermissions(permissions, requestCode)
        }
    }

    private fun openApplicationSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:" + this.packageName)
        )
        startActivityForResult(appSettingsIntent, requestCode)
    }

    private fun initToolbar(){
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
                    navigateToDetails(response.id)
                } catch (ex:IOException){
                    Snackbar.make(findViewById(android.R.id.content), "No such city was found", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
            return true
        } else false
    }

    private fun navigateToDetails(id: Int){
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

}
