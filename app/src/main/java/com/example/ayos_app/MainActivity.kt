package com.example.ayos_app

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ayos_app.ui.theme.Ayos_AppTheme
import androidx.navigation.NavController
import com.example.ayos_app.databinding.ActivityMainBinding
import com.example.ayos_app.databinding.ProfileBinding
import com.example.ayos_app.databinding.ReportStatusBinding


class MainActivity : ComponentActivity() {override fun onCreate(savedInstanceState: Bundle?) {
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
                    LoginScreen(
                        onBack = { navController.popBackStack() },
                        onLoginSuccess = {navController.navigate("user_dashboard")}
                    )
                }
                composable("signup") {
                    SignupScreen(onBack = { navController.popBackStack() })
                }
                composable("user_dashboard") {
                    MainDashboardScreen(navController)
                }
                composable("profile") {
                    ProfileScreen(navController)
                }
                composable("report_status") {
                    ReportListScreen(navController)
                }
            }
        }
    }
}
}
@Composable
fun MainDashboardScreen(navController: NavController) {
    AndroidViewBinding(factory = ActivityMainBinding::inflate) {

        // Example progress
        val currentProgress = 50 // Example: 50%
        val maxProgress = 100 // Example: 100%
        progressBar.progress = currentProgress
        progressBar.max = maxProgress

        val resolvedPercentage = progressBar.progress
        val unresolvedPercentage = progressBar.max - progressBar.progress // Assumes max is 100

        resolvedTextView.text = "$resolvedPercentage% resolved issue"
        unresolvedTextView.text = "$unresolvedPercentage% unresolved issue"


        reportButton.setOnClickListener {
            Toast.makeText(root.context, "Report button clicked", Toast.LENGTH_SHORT).show()
            // This is just a placeholder. You'll need to update based on your app's logic.
            val newProgress = (progressBar.progress + 10).coerceAtMost(progressBar.max)
            progressBar.progress = newProgress

            // Update text views again after progress changes
            val updatedResolvedPercentage = progressBar.progress
            val updatedUnresolvedPercentage = progressBar.max - progressBar.progress
            resolvedTextView.text = "$updatedResolvedPercentage% resolved issue"
            unresolvedTextView.text = "$updatedUnresolvedPercentage% unresolved issue"
        }

        homeButton.setOnClickListener {
            navController.navigate("user_dashboard") {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
        profileButton.setOnClickListener {
            navController.navigate("profile")
        }
        reportListButton.setOnClickListener {
            navController.navigate("report_status")
        }
    }
}

@Composable
fun ProfileScreen(navController: NavController) {
    AndroidViewBinding(factory = ProfileBinding::inflate){
        homeButton.setOnClickListener {
            navController.navigate("user_dashboard")
        }
        profileButton.setOnClickListener {
            navController.navigate("profile") {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }

        }
        reportListButton.setOnClickListener {
            navController.navigate("report_status")
        }
    }
}

@Composable
fun ReportListScreen(navController: NavController) {
    AndroidViewBinding(factory = ReportStatusBinding::inflate){
        homeButton.setOnClickListener {
            navController.navigate("user_dashboard")
        }
        profileButton.setOnClickListener {
            navController.navigate("profile")
        }
        reportListButton.setOnClickListener {
            navController.navigate("report_status") {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }

        }
    }
}