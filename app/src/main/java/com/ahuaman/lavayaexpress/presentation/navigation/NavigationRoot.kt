package com.ahuaman.lavayaexpress.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahuaman.lavayaexpress.R
import com.ahuaman.lavayaexpress.presentation.screens.DashboardScreen
import com.ahuaman.lavayaexpress.presentation.screens.HelpScreen
import com.ahuaman.lavayaexpress.presentation.screens.HomeScreen
import com.ahuaman.lavayaexpress.presentation.screens.SplashScreen

@Composable
fun NavigationRoot(
    modifier : Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME,
        modifier = modifier
    ) {
        composable(Graph.SPLASH) {
            SplashScreen(onNextScreen = { navController.navigate(route = Graph.HOME) })
        }

        composable(Graph.HOME) {
            val navHostController = rememberNavController()
            DashboardScreen(navHostController)
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
            HomeScreen()
        }

        composable(HomeScreens.HelpScreen.route) {
            HelpScreen()
        }

    }
    
}

sealed class HomeScreens(val route:String, val title:String, val icon:Int) {
    object HomeScreen : HomeScreens("home_screen", "Home", R.drawable.ic_home)
    object HelpScreen : HomeScreens("help_screen", "Help", R.drawable.ic_help)
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val SPLASH = "splash_graph"
}