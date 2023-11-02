package com.ahuaman.lavayaexpress.presentation.viewmodel

import com.google.android.gms.maps.model.LatLng

sealed class LocationStates {
    object NoPermission: LocationStates()
    object LocationDisabled: LocationStates()
    object LocationLoading: LocationStates()
    data class LocationAvailable(val location: LatLng): LocationStates()
    object Error: LocationStates()
}
