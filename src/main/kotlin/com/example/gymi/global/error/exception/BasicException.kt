package com.example.gymi.global.error.exception

import com.example.gymi.global.error.ErrorCode

class BasicException(

        val errorCode: ErrorCode
): RuntimeException()