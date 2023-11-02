package com.ahuaman.lavayaexpress.presentation.navigation

import android.Manifest
import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahuaman.lavayaexpress.R
import com.ahuaman.lavayaexpress.presentation.screens.DashboardScreen
import com.ahuaman.lavayaexpress.presentation.screens.HelpScreen
import com.ahuaman.lavayaexpress.presentation.screens.HomeScreen
import com.ahuaman.lavayaexpress.presentation.screens.SplashScreen
import com.ahuaman.lavayaexpress.presentation.viewmodel.HomeViewModel
import com.ahuaman.lavayaexpress.utils.findActivity
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices

@Composable
fun NavigationRoot(
    modifier : Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH,
        modifier = modifier
    ) {
        composable(Graph.SPLASH) {
            SplashScreen(onNextScreen = {
                navController.navigate(Graph.HOME) {
                    popUpTo(Graph.SPLASH) {
                        inclusive = true
                    }
                }
            })
        }

        composable(Graph.HOME) {
            DashboardScreen()
        }

    }

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    val context = LocalContext.current

    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeScreens.HomeScreen.route,
    ) {
        composable(HomeScreens.HomeScreen.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            homeViewModel.fusedLocationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)
            val stateDirection by homeViewModel.directionState.collectAsStateWithLifecycle()

            val locationPermissionState = rememberMultiplePermissionsState(
                listOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )

            val activity = LocalContext.current.findActivity()

            LaunchedEffect(locationPermissionState.allPermissionsGranted) {
                if (locationPermissionState.allPermissionsGranted) {
                    if (locationEnabled(activity)) {
                        homeViewModel.getCurrentLocation()
                    } else {
                        homeViewModel.disableLocation()
                    }
                }
            }
            

            HomeScreen(
                stateDirection = stateDirection,
                onChangeLocation = { direction ->
                    homeViewModel.setDirection(direction = direction)
                }
            )


        }

        composable(HomeScreens.HelpScreen.route) {
            HelpScreen()
        }

    }
}

private fun locationEnabled(activity:Activity?): Boolean {
    val locationManager = activity?.getSystemService(android.content.Context.LOCATION_SERVICE) as android.location.LocationManager
    return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)
}



sealed class HomeScreens(val route:String, val title:String, val icon:Int) {
    object HomeScreen : HomeScreens("home_screen", "Inicio", R.drawable.ic_home)
    object HelpScreen : HomeScreens("help_screen", "Ayuda", R.drawable.ic_help)
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val SPLASH = "splash_graph"
}