package com.example.agrometeo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrometeo.ui.Home.HomeScreen
import com.example.agrometeo.ui.login.Login

@Composable
fun AgroMeteoApp(){
    val navController = rememberNavController();
    NavHost(navController = navController, startDestination = Destinations.LOGIN){
        composable(route = Destinations.LOGIN) {
            Login(
                //define the callback for the route
                onLogin={
                    navController.navigate(route = Destinations.HOME){
                        popUpTo(Destinations.LOGIN) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(route = Destinations.HOME) {
            HomeScreen()
        }
    }
}
