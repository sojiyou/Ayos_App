package com.example.ayos_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ayos_app.ui.theme.Ayos_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ayos_AppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "landing") {
                    composable("landing") {
                        LandingScreen(
                            onLoginClick = { navController.navigate("login") },
                            onSignupClick = { navController.navigate("signup") }
                        )
                    }
                    composable("login") {
                        LoginScreen(onBack = { navController.popBackStack() })
                    }
                    composable("signup") {
                        SignupScreen(onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}