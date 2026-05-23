package com.baha.sushigarden.data.services.delivery

object FieldValidators {
    private val emailRegex = Regex("^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    fun isValidEmail(email: String): Boolean = emailRegex.matches(email)

    fun isValidPassword(password: String): Boolean = password.length >= 6

    fun isValidPhone(phone: String): Boolean = phone.filter { it.isDigit() }.length >= 10

    fun isNonEmpty(value: String): Boolean = value.trim().isNotEmpty()
}
