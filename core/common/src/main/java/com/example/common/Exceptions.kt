package com.example.common

class EmptyFieldException(
    message: String = "",
    cause: Throwable? = null
) : Exception(message, cause)