package com.ahuaman.lavayaexpress.presentation.screens

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahuaman.lavayaexpress.R
import com.ahuaman.lavayaexpress.presentation.screens.custom.IndeterminateLinearProgressIndicator
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants.IterateForever
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNextScreen: () -> Unit) {


    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.anim_washer)
    )


    LaunchedEffect(key1 = true) {
        delay(2000L)
        onNextScreen()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Image(painter = painterResource(id = R.drawable.ic_logo_lavaya), contentDescription = null )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-100).dp)
        ) {
            LottieAnimation(
                modifier = Modifier.fillMaxWidth().size(300.dp),
                composition = composition,
                iterations = IterateForever
            )

            IndeterminateLinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                height = 20.dp,
                progressColor = colorResource(id = R.color.colorPrimary),
                backgroundColor = Color.LightGray
            )
        }


    }
}


@Preview
@Composable
fun SplashPreview() {
    SplashScreen {

    }
}