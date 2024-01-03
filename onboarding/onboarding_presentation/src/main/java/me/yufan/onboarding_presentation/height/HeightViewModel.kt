package me.yufan.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.yufan.core.R
import me.yufan.core.navigation.Route
import me.yufan.core.util.UiEvent
import me.yufan.core.util.UiText
import me.yufan.core_domain.model.BaseUnit
import me.yufan.core_domain.preferences.Preferences
import me.yufan.onboarding_domain.use_case.ConvertUnits
import me.yufan.onboarding_domain.use_case.FilterOutDigits
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val convertUnits: ConvertUnits
) : ViewModel() {
    var height by mutableStateOf("180")
        private set
    var selectedHeightUnit by mutableStateOf<BaseUnit>(BaseUnit.Centimeter)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onHeightEnter(height: String) {
        if (height.length <= 3) {
            this.height = when (selectedHeightUnit) {
                is BaseUnit.Centimeter -> filterOutDigits(height, false)
                is BaseUnit.Feet -> filterOutDigits(height, true)
                else -> ""
            }
        } else
            this.height = filterOutDigits(height.take(5), true)
    }

    fun onUnitSwitch() {
        selectedHeightUnit = when (selectedHeightUnit) {
            is BaseUnit.Centimeter -> BaseUnit.Feet
            is BaseUnit.Feet -> BaseUnit.Centimeter
            else -> BaseUnit.Empty
        }

        onHeightEnter(convertUnits(height, selectedHeightUnit))
    }

    fun onNextClick() {
        viewModelScope.launch {
            val heightNumber = when (selectedHeightUnit) {
                BaseUnit.Feet -> convertUnits(height, BaseUnit.Centimeter)
                BaseUnit.Centimeter -> height
                else -> height
            }.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        UiText.StringResource(R.string.error_height_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveHeight(heightNumber)
            _uiEvent.send(UiEvent.Navigate(Route.WEIGHT))
        }
    }
}