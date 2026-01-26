package com.example.myapplication.utils

object ValidationUtils {

    fun isValidChileanRut(rut: String): Boolean {
        if (rut.length < 3) return false

        val cleanRut = rut.replace(".", "").replace("-", "").uppercase()
        val body = cleanRut.dropLast(1)
        val dv = cleanRut.last()

        if (!body.all { it.isDigit() } || !(dv.isDigit() || dv == 'K')) {
            return false
        }

        var sum = 0
        var multiplier = 2

        for (i in body.reversed()) {
            sum += i.toString().toInt() * multiplier
            multiplier++
            if (multiplier == 8) multiplier = 2
        }

        val calculatedDv = when (val mod = 11 - (sum % 11)) {
            11 -> '0'
            10 -> 'K'
            else -> mod.toString().first()
        }

        return dv == calculatedDv
    }

    fun isValidName(name: String): Boolean {
        return name.all { it.isLetter() || it.isWhitespace() }
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPhoneNumber(phone: String): Boolean {
        return phone.all { it.isDigit() } && phone.length >= 8
    }
}
