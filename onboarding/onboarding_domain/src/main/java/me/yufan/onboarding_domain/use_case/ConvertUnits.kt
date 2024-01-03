package me.yufan.onboarding_domain.use_case

import me.yufan.core_domain.model.BaseUnit
import me.yufan.core_domain.util.roundToDecimal
import kotlin.math.roundToInt

class ConvertUnits {
    operator fun invoke(
        amount: String,
        toUnit: BaseUnit
    ): String {
        return when (toUnit) {
            is BaseUnit.Feet ->
                ((amount.toIntOrNull() ?: 0) / 30.48).toString()

            is BaseUnit.Centimeter ->
                ((amount.toFloatOrNull() ?: 0f) * 30.48).roundToInt().toString()

            is BaseUnit.Pound ->
                ((amount.toFloatOrNull() ?: 0f) * 2.20).roundToDecimal(2).toString()

            is BaseUnit.Kilogram ->
                ((amount.toFloatOrNull() ?: 0f) / 2.20).roundToDecimal(2).toString()

            else -> ""
        }
    }
}