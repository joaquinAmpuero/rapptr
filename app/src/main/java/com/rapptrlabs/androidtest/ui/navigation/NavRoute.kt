package com.rapptrlabs.androidtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rapptrlabs.androidtest.ui.chat.ChatScreen
import com.rapptrlabs.androidtest.ui.home.HomeScreen
import androidx.navigation.compose.composable
import com.rapptrlabs.androidtest.ui.animation.AnimationScreen
import com.rapptrlabs.androidtest.ui.login.LoginScreen
import com.rapptrlabs.androidtest.ui.splash.SplashScreen

sealed class NavRoute(val path: String) {
    object Home : NavRoute("Home")
    object Login : NavRoute("Login")
    object Chat : NavRoute("Chat")
    object Animation : NavRoute("Animation")
    object Splash : NavRoute("Splash")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoute.Splash.path) {
        composable(NavRoute.Home.path) { HomeScreen(navController) }
        composable(NavRoute.Chat.path) { ChatScreen(navController, hiltViewModel()) }
        composable(NavRoute.Login.path) { LoginScreen(navController, hiltViewModel()) }
        composable(NavRoute.Animation.path) { AnimationScreen(navController) }
        composable(NavRoute.Splash.path) { SplashScreen(navController) }
    }
}
