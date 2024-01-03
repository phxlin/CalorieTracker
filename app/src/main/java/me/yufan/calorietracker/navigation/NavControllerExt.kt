package me.yufan.calorietracker.navigation

import androidx.navigation.NavController
import me.yufan.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}