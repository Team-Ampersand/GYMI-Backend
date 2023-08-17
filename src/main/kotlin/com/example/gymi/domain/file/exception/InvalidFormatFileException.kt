package com.example.gymi.domain.file.exception

import com.example.gymi.global.error.exception.BasicException
import com.example.gymi.global.error.exception.ErrorCode

class InvalidFormatFileException : BasicException(ErrorCode.INVALID_FORMAT_FILE) {
}