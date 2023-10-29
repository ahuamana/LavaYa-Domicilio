package com.ahuaman.lavayaexpress.presentation.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun HomeScreen() {

    val uiSettings = MapUiSettings(
        compassEnabled = true,
        zoomControlsEnabled = false,
        scrollGesturesEnabled = true,
        tiltGesturesEnabled = true,
    )

    //Default location Huancayo - Peru
    Box(modifier = Modifier.fillMaxSize()) {
        val defaultLocation = LatLng(-12.070830, 75.206680)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(defaultLocation, 15f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
            onMapClick = { /* TODO */ },
        ) {/* TODO */}

        //Marker center
        MarkerCenter()

        var latitud by rememberSaveable { mutableStateOf(0.0) }
        var longitud by rememberSaveable { mutableStateOf(0.0) }

        latitud = cameraPositionState.position.target.latitude
        longitud = cameraPositionState.position.target.longitude



        //Content
        HomeContent(
            "Lat: $latitud, Lng: $longitud"
        )

        //Lat and Lng
        /*Text(text = "is Camera Moving: ${cameraPositionState.isMoving}" +
                "\nLat: ${cameraPositionState.position.target.latitude}" +
                "\nLng: ${cameraPositionState.position.target.longitude}",
            textAlign = TextAlign.Center)*/
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

        //var query by remember { mutableStateOf("") }
        var text by rememberSaveable { mutableStateOf("") }
        var onSearch by remember { mutableStateOf("") }
        var active by rememberSaveable { mutableStateOf(false) }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        ) {
            Text(text = "¿A dónde quiere que lleguemos?",
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
                    onValueChange = { onValueChange = it },
                    label = { Text(text = "Ingresa su dirección") },

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
                        text = "Solicitar Servicio",
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
    HomeScreen()
}