package com.example.dynadroid

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dynadroid.ui.on_boarding.Welcome
import com.example.dynadroid.ui.on_boarding.select_apps.AppSelectionScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "onboarding") {
        appNav(navController)
    }
}

private fun NavGraphBuilder.appNav(
    navController: NavHostController
) {
    navigation(startDestination = "welcome", route = "onboarding") {
        composable(route = "welcome") {
            Welcome(
                onNextClick = {
                    navController.navigate("appSelectionOnboarding") {
                        popUpTo("welcome") {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = "appSelectionOnboarding") {
            AppSelectionScreenRoot(onNextClick = {}, onSkipClick = {})
        }
    }
}