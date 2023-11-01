package com.ahuaman.lavayaexpress.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
            SplashScreen(onNextScreen = { navController.navigate(route = Graph.HOME) })
        }

        composable(Graph.HOME) {
            DashboardScreen()
        }

    }

}

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeScreens.HomeScreen.route,
    ) {
        composable(HomeScreens.HomeScreen.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()

            val stateDirection by homeViewModel.directionState.collectAsStateWithLifecycle()

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

sealed class HomeScreens(val route:String, val title:String, val icon:Int) {
    object HomeScreen : HomeScreens("home_screen", "Inicio", R.drawable.ic_home)
    object HelpScreen : HomeScreens("help_screen", "Ayuda", R.drawable.ic_help)
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val SPLASH = "splash_graph"
}