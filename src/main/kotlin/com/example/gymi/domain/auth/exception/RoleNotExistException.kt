package com.example.gymi.domain.auth.exception

import com.example.gymi.global.error.exception.BasicException
import com.example.gymi.global.error.exception.ErrorCode

class RoleNotExistException : BasicException(ErrorCode.ROLE_NOT_EXIST) {
}