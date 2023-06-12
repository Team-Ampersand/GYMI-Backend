package com.example.gymi.domain.user.exception

import com.example.gymi.global.error.exception.BasicException
import com.example.gymi.global.error.exception.ErrorCode

class UserNotFoundException : BasicException(ErrorCode.USER_NOT_FOUND) {
}