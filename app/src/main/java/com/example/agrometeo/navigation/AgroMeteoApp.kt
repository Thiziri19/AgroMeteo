package com.example.agrometeo.navigation

import androidx.compose.runtime.Composable
import androidx.core.os.ProfilingRequest
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrometeo.ui.Home.HomeScreen
import com.example.agrometeo.ui.homeScreens.CulturesScreen
import com.example.agrometeo.ui.homeScreens.ForcastsScreen
import com.example.agrometeo.ui.homeScreens.MapScreen
import com.example.agrometeo.ui.homeScreens.ProfileScreen
import com.example.agrometeo.ui.login.Login

@Composable
fun AgroMeteoApp(){
    val navController = rememberNavController();
    val selectedNavItem : (HomeContent)->Unit = { tabItem -> navController.navigate(route = tabItem.route){
        popUpTo(Destinations.CULTURES) { saveState = true }
        launchSingleTop = true
        restoreState = true
     }
    }
    NavHost(navController = navController, startDestination = Destinations.LOGIN){
        composable(route = Destinations.LOGIN) {
            Login(
                //define the callback for the route
                onLogin={
                    navController.navigate(route = Destinations.CULTURES){
                        popUpTo(Destinations.LOGIN) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        //For the homeTabs we need to pass from the Home and define the callBack to the right
        //screen for the content
        composable(route = Destinations.CULTURES) {
            HomeScreen(currentHomeTab = HomeContent.CULTURES, onSelectHomeTab = selectedNavItem, content = {
                //for showing the content while opening the HomeScreen
                CulturesScreen()
            })
        }

        composable(route = Destinations.MAP   ) {
            HomeScreen(currentHomeTab = HomeContent.MAP, onSelectHomeTab = selectedNavItem, content = {
                //for showing the content while opening the HomeScreen
                MapScreen()
            })

        }
        composable(route = Destinations.FORCASTS) {
            HomeScreen(currentHomeTab = HomeContent.FORCASTS, onSelectHomeTab = selectedNavItem, content = {
                //for showing the content while opening the HomeScreen
                ForcastsScreen()
            })
        }
        composable(route = Destinations.PROFILE) {
            HomeScreen(currentHomeTab = HomeContent.PROFILE, onSelectHomeTab = selectedNavItem, content = {
                //for showing the content while opening the HomeScreen
                ProfileScreen()
            })

        }
    }
}
