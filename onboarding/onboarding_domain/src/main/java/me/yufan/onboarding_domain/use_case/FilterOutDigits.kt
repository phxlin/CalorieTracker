package me.yufan.onboarding_domain.use_case

class FilterOutDigits {
    operator fun invoke(text: String, isDecimalAllowed: Boolean): String {
        return if (isDecimalAllowed)
            text.filter { it.isDigit() || it == '.' }
        else
            text.filter { it.isDigit() }
    }
}