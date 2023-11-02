package com.ahuaman.lavayaexpress.presentation.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahuaman.lavayaexpress.R
import com.ahuaman.lavayaexpress.presentation.navigation.HomeNavGraph
import com.ahuaman.lavayaexpress.presentation.navigation.HomeScreens
import com.google.maps.android.compose.GoogleMap

@Composable
fun DashboardScreen(
    navController: NavHostController = rememberNavController()
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            //BottomBarCustom(navController = navController)
        }
    ) { innerPadding ->

        //content
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            HomeNavGraph(navController = navController, innerPadding = innerPadding)
            TopMenuBar(
                onClickHelp = {
                    if(navController.currentDestination?.route != HomeScreens.HelpScreen.route) navController.navigate(HomeScreens.HelpScreen.route)
                              },
                onClickHome = {
                    if(navController.currentDestination?.route != HomeScreens.HomeScreen.route) navController.navigate(HomeScreens.HomeScreen.route)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMenuBar(
    onClickHelp: () -> Unit = {},
    onClickHome: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = { onClickHome() }) {
            Icon(
                modifier = Modifier
                    .padding(6.dp),
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = null)
        }

        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = { onClickHelp() }) {
            Icon(
                modifier = Modifier
                    .padding(6.dp),
                painter = painterResource(id = R.drawable.ic_help),
                contentDescription = null)
        }
    }
}


@Composable
fun BottomBarCustom(navController: NavHostController){
    val menuItems = listOf(
        HomeScreens.HomeScreen,
        HomeScreens.HelpScreen,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = menuItems.any { it.route == currentDestination?.route }

    NavigationBar(
        modifier = Modifier.height(80.dp),
        containerColor = Color.Black.copy(alpha = 0.8f),
    ) {
        menuItems.forEach { screen ->
            //setup the alpha for the selected item
            val isSelectedMenu = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val backgroundAlpha = if (isSelectedMenu) 1f else 0.6f

            NavigationBarItem(
                label = {
                    Text(text = screen.title, color = Color.White.copy(alpha = backgroundAlpha)) },
                icon = { Icon(painterResource(id = screen.icon), contentDescription = screen.title, modifier = Modifier.graphicsLayer(alpha = backgroundAlpha)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                //selectedContentColor = Color.White,
                //unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}


@Preview
@Composable
fun DashboardPreview() {
    DashboardScreen()
}
