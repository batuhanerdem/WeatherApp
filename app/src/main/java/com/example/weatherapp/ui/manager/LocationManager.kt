package com.example.weatherapp.ui.manager

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import java.util.Locale

object LocationManager {
    fun getLocation(
        askForPermissionCallback: () -> Unit,
        successCallback: (List<Address>) -> Unit,
        errorCallback: (Exception) -> Unit,
        context: Context
    ) {
        val coder = Geocoder(context, Locale.getDefault())
        val locationClient = LocationServices.getFusedLocationProviderClient(context)
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            askForPermissionCallback()
            return
        }
        locationClient.lastLocation.addOnSuccessListener { location ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                try {// can get crash for some reason
                    coder.getFromLocation(location.latitude, location.longitude, 1) {
                        successCallback(it)
                        Log.d("tag", "try catch success ")
                    }
                } catch (e: Exception) {
                    Log.d("tag", "try catch error $e")
                    errorCallback(e)
                }

            } else {
                try {
                    val result = coder.getFromLocation(location.latitude, location.longitude, 1)
                    if (result!!.isNotEmpty()) {
                        successCallback(result)
                    } else {
                        errorCallback(Exception("No geocoding results found"))
                    }
                } catch (e: Exception) {
                    errorCallback(e)
                }
            }
        }
    }

}