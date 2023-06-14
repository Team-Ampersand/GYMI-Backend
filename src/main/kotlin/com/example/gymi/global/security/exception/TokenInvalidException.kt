package com.example.gymi.global.security.exception

import com.example.gymi.global.error.exception.BasicException
import com.example.gymi.global.error.exception.ErrorCode

class TokenInvalidException : BasicException(ErrorCode.TOKEN_NOT_VALID) {
}