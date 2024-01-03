package me.yufan.core_domain.model

sealed class BaseUnit(val name: String) {
    object Centimeter : BaseUnit("cm")
    object Feet : BaseUnit("ft")
    object Kilogram : BaseUnit("kg")
    object Pound : BaseUnit("lbs")
    object Empty : BaseUnit("")

    companion object {
        fun fromString(name: String): BaseUnit {
            return when (name) {
                "cm" -> Centimeter
                "ft" -> Feet
                "kg" -> Kilogram
                "lbs" -> Pound
                else -> Empty
            }
        }
    }
}