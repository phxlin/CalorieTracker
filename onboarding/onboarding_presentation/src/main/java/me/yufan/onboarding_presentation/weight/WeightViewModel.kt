package me.yufan.onboarding_presentation.weight

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
class WeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val convertUnits: ConvertUnits
) : ViewModel() {
    var weight by mutableStateOf("80.0")
        private set
    var selectedWeightUnit by mutableStateOf<BaseUnit>(BaseUnit.Kilogram)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightEnter(weight: String) {
        if (weight.length <= 5) {
            this.weight = filterOutDigits(weight, true)
        } else
            this.weight = filterOutDigits(weight.take(5), true)
    }

    fun onUnitSwitch() {
        selectedWeightUnit = when (selectedWeightUnit) {
            is BaseUnit.Kilogram -> BaseUnit.Pound
            is BaseUnit.Pound -> BaseUnit.Kilogram
            else -> BaseUnit.Empty
        }

        onWeightEnter(convertUnits(weight, selectedWeightUnit))
    }

    fun onNextClick() {
        viewModelScope.launch {
            val weightNumber = when (selectedWeightUnit) {
                BaseUnit.Pound -> convertUnits(weight, BaseUnit.Kilogram)
                BaseUnit.Kilogram -> weight
                else -> weight
            }.toFloatOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        UiText.StringResource(R.string.error_weight_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveWeight(weightNumber)
            _uiEvent.send(UiEvent.Navigate(Route.ACTIVITY))
        }
    }
}