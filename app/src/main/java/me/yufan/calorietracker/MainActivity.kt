package me.yufan.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.yufan.calorietracker.navigation.navigate
import me.yufan.calorietracker.ui.theme.CaloryTrackerTheme
import me.yufan.core.navigation.Route
import me.yufan.onboarding_presentation.activity_level.ActivityLevelScreen
import me.yufan.onboarding_presentation.age.AgeScreen
import me.yufan.onboarding_presentation.gender.GenderScreen
import me.yufan.onboarding_presentation.goal_type.GoalTypeScreen
import me.yufan.onboarding_presentation.height.HeightScreen
import me.yufan.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import me.yufan.onboarding_presentation.weight.WeightScreen
import me.yufan.onboarding_presentation.welcome.WelcomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    content = { padding ->
                        NavHost(
                            modifier = Modifier
                                .padding(padding),
                            navController = navController,
                            startDestination = Route.WELCOME
                        ) {
                            composable(Route.WELCOME) {
                                WelcomeScreen(onNavigate = navController::navigate)
                            }
                            composable(Route.AGE) {
                                AgeScreen(
                                    scaffoldState = scaffoldState,
                                    onNavigate = navController::navigate
                                )
                            }
                            composable(Route.GENDER) {
                                GenderScreen(onNavigate = navController::navigate)
                            }
                            composable(Route.HEIGHT) {
                                HeightScreen(
                                    scaffoldState = scaffoldState,
                                    onNavigate = navController::navigate
                                )
                            }
                            composable(Route.WEIGHT) {
                                WeightScreen(
                                    scaffoldState = scaffoldState,
                                    onNavigate = navController::navigate
                                )
                            }
                            composable(Route.NUTRIENT_GOAL) {
                                NutrientGoalScreen(
                                    scaffoldState = scaffoldState,
                                    onNavigate = navController::navigate
                                )
                            }
                            composable(Route.ACTIVITY) {
                                ActivityLevelScreen(
                                    onNavigate = navController::navigate
                                )
                            }
                            composable(Route.GOAL) {
                                GoalTypeScreen(
                                    onNavigate = navController::navigate
                                )
                            }
                            composable(Route.TRACKER_OVERVIEW) {

                            }
                            composable(Route.SEARCH) {

                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                )
            }
        }
    }
}