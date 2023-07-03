package com.example.gymi.global.error.exception

open class BasicException(
    val errorCode: ErrorCode
) : RuntimeException()