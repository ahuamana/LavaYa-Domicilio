package com.ahuaman.lavayaexpress.presentation.screens

import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahuaman.lavayaexpress.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapsSdkInitializedCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun HomeScreen(
    stateDirection:String,
    onChangeLocation: (String) -> Unit = {},
) {

    val context = LocalContext.current

    val uiSettings = MapUiSettings(
        compassEnabled = true,
        zoomControlsEnabled = false,
        scrollGesturesEnabled = true,
        tiltGesturesEnabled = true,
    )

    //Huancayo Peru
    val huancayo = LatLng(-12.0652, -75.2049)
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    //Default location Huancayo - Peru
    Box(modifier = Modifier.fillMaxSize()) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(huancayo, 15f))
            },
        )

        //Marker center
        MarkerCenter()

        var latitud by rememberSaveable { mutableStateOf(0.0) }
        var longitud by rememberSaveable { mutableStateOf(0.0) }

        latitud = cameraPositionState.position.target.latitude
        longitud = cameraPositionState.position.target.longitude

        val addressAll = Geocoder(context).getFromLocation(
            cameraPositionState.position.target.latitude,
            cameraPositionState.position.target.longitude,
            1,
        )

        //Make variable for address
        val addressText = addressAll?.firstOrNull()?.getAddressLine(0) ?: "No address found"
       onChangeLocation(addressText)

        println("addressText: $addressText")

        //Content
        HomeContent(
            stateDirection
        )
    }

}


@Composable
fun MarkerCenter() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_pin_map), contentDescription = null)
    }
}



@Composable
fun HomeContent(
    onSearch: String = "",
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var text by rememberSaveable { mutableStateOf("") }
        var active by rememberSaveable { mutableStateOf(false) }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        ) {
            Text(text = "¿A dónde desea que lleguemos?",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.googlesans_regular)),)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
            ) {
                //val value by remember { mutableStateOf("") }
                var onValueChange by remember { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = onSearch,
                    onValueChange = { value ->
                        onValueChange = value
                    },
                    label = { Text(text = "Ingrese su dirección") },

                    leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = null) },
                    //trailingIcon = { Icon(Icons.Rounded.MoreVert, contentDescription = null) },
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.colorPrimary),
                        contentColor = Color.White,
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        fontSize = 18.sp,
                        text = "Solicitar servicio",
                        fontFamily = FontFamily(Font(R.font.googlesans_regular)),
                    )
                }
            }
        }






    }
}

@Preview
@Composable
fun HomeScreenPrev() {
    HomeScreen(
        stateDirection = "Huancayo - Peru"
    )
}