package com.example.gymi.global.security.exception

import com.example.gymi.global.error.exception.BasicException
import com.example.gymi.global.error.exception.ErrorCode

class TokenExpiredException : BasicException(ErrorCode.TOKEN_IS_EXPIRED) {
}