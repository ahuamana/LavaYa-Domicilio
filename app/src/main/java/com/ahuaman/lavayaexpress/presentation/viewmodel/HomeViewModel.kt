package com.ahuaman.lavayaexpress.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.renderscript.RenderScript
import android.renderscript.RenderScript.Priority
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.OnTokenCanceledListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var geoCoder: Geocoder

    var locationState = MutableStateFlow<LocationStates>(LocationStates.NoPermission)


    private val direction = MutableStateFlow("")
    var directionState = direction.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    private val _currentLocation = MutableStateFlow(LatLng(0.0, 0.0))
    var currentLocation = _currentLocation.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LatLng(0.0, 0.0)
    )

    fun setDirection(direction: String) {
        println("direction: $direction")
        this.direction.value = direction
    }

    fun saveCurrentLocationLatLng(location: LatLng) {
        _currentLocation.value = location
    }

    fun initGeoCoder(context:Context) {
        geoCoder = Geocoder(context)
    }

    fun initFusedLocationClient(context:Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    fun updateDirectionFromLocation(location: LatLng) {
        //Set current location
        saveCurrentLocationLatLng(location)
        //Get direction from location
        val address = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
        address?.let {
            if (it.isNotEmpty()) {
                val addressLine = it[0].getAddressLine(0)
                setDirection(addressLine)
            }
        }?: kotlin.run {
            setDirection("Dirección no disponible")
        }
    }


    @SuppressLint("MissingPermission")
    fun getCurrentLocation() {
        locationState.value = LocationStates.LocationLoading
        fusedLocationClient
            .getCurrentLocation(PRIORITY_HIGH_ACCURACY, onCancellationToken)
            .addOnSuccessListener { location ->
                location?.let {
                    locationState.value = LocationStates.LocationAvailable(LatLng(it.latitude, it.longitude))
                }?: kotlin.run {
                    locationState.value = LocationStates.Error
                }
            }
    }

    fun disableLocation() {
        locationState.value = LocationStates.LocationDisabled
    }


    private val onCancellationToken = object : CancellationToken() {
        override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
            return this
        }

        override fun isCancellationRequested(): Boolean {
            return false
        }
    }



}