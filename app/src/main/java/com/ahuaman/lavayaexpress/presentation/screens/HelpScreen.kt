package com.ahuaman.lavayaexpress.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahuaman.lavayaexpress.R
import com.ahuaman.lavayaexpress.ui.theme.LavaYaExpressTheme

@Composable
fun HelpScreen() {
    var numberPieces by rememberSaveable { mutableStateOf("") }
    val ivWhatsApp = painterResource(id = R.drawable.ic_whatsapp)

    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 80.dp),
    ) {
        Box (modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                /*OutlinedTextField(value = numberPieces, onValueChange = { numberPieces = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Número de piezas") }
                )*/

                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Solicitar recojo")
                }
            }

            //Floating button
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End,
            ) {
                Button(
                    modifier = Modifier.padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = androidx.compose.ui.graphics.Color(0xFF25D366),
                        contentColor = androidx.compose.ui.graphics.Color.White
                    ),
                    onClick = {

                    }) {
                    Icon(painter = ivWhatsApp, contentDescription = null )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Hazme una pregunta")
                }
            }
        }
    }
}


@Preview
@Composable
fun HelpPreview() {
    LavaYaExpressTheme {
        HelpScreen()
    }
}