package me.yufan.core_domain.util

import kotlin.math.round

fun Double.roundToDecimal(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}