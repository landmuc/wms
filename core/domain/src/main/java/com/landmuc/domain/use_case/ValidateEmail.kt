package com.landmuc.domain.use_case


class ValidateEmail {
    operator fun invoke(
        email: String
    ): Boolean {
        val emailRegex = Regex(
            "[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}"
        )
        return emailRegex.matches(email)
    }
}
