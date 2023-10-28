package com.ahuaman.lavayaexpress.presentation.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahuaman.lavayaexpress.R
import com.ahuaman.lavayaexpress.ui.theme.LavaYaExpressTheme
import com.ahuaman.lavayaexpress.ui.theme.Typography

@Composable
fun HelpScreen() {
    val ivWhatsApp = painterResource(id = R.drawable.ic_whatsapp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Box (modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Image(painter = painterResource(id = R.drawable.ic_logo_lavaya), contentDescription = null)

                // Agrega un Text encima del "Floating button" para motivar a los usuarios a enviar un mensaje
                Text(
                    text = "¿Necesitas ayuda? ¡Estamos aquí para ayudarte! Si tienes alguna pregunta o inquietud, no dudes en contactarnos a través de WhatsApp.",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    //style = Typography.labelMedium,
                    fontFamily = FontFamily(Font(R.font.googlesans_regular))
                )

                //Floating button
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
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
}


@Preview
@Composable
fun HelpPreview() {
    LavaYaExpressTheme {
        HelpScreen()
    }
}