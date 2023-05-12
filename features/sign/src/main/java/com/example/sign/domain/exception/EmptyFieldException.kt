package com.example.sign.domain.exception

import com.example.sign.domain.entity.SignUserFields

class EmptyFieldException(
    val field: SignUserFields
) : Exception()