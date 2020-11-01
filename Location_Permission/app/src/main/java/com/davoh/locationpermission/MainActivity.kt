package com.davoh.locationpermission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestAccesFineLocationPermission()


    }

    private fun displayLocationSettingsRequest() {

        val mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000.toLong())
                .setFastestInterval(1 * 1000.toLong())

        val settingsBuilder = LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest)
        settingsBuilder.setAlwaysShow(true)

        val result = LocationServices.getSettingsClient(this)
                .checkLocationSettings(settingsBuilder.build())

        Toast.makeText(this, "Dialog initialized", Toast.LENGTH_SHORT).show()

        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(ApiException::class.java)
                Toast.makeText(this, "Dialog showed", Toast.LENGTH_SHORT).show()

            } catch (ex: ApiException) {
                when (ex.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val resolvableApiException = ex as ResolvableApiException
                        resolvableApiException.startResolutionForResult(this, GPS_REQUEST_CODE)
                    } catch (e: SendIntentException) {

                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                    }
                }
            }

        }



    }

    private fun requestAccesFineLocationPermission() {

        when {
            ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                //performAction(...)
                Toast.makeText(this, "This persission is granted before", Toast.LENGTH_SHORT).show()
                displayLocationSettingsRequest()

                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

                fusedLocationClient.lastLocation
                        .addOnSuccessListener { location: Location? ->
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                txtLatitude.text = location.latitude.toString()
                                txtLongitude.text = location.longitude.toString()
                            }
                        }
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            //showInContextUI(...)
                Toast.makeText(this, "This persission is not granted", Toast.LENGTH_SHORT).show()
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PEMISSION_CODE_ACCES_FINE_LOCATION)
        }
            else -> {
                // You can directly ask for the permission.
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PEMISSION_CODE_ACCES_FINE_LOCATION)
            }
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PEMISSION_CODE_ACCES_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                                grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    Toast.makeText(this, "This persission is granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "This is a feature unavaible because require a permission", Toast.LENGTH_SHORT).show()
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==GPS_REQUEST_CODE){
            Toast.makeText(this, "GPS ENABLED", Toast.LENGTH_SHORT).show()
            //Log.d(TAG, "onActivityResult: requestCode: $requestCode")
        }
    }



    companion object {
        private const val PEMISSION_CODE_ACCES_FINE_LOCATION = 110
        private const val GPS_REQUEST_CODE = 121
        private const val TAG = "MainActivity"
    }
}