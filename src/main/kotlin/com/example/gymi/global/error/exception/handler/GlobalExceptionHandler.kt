package com.example.gymi.global.error.exception.handler

import com.example.gymi.global.error.exception.ErrorCode
import com.example.gymi.global.error.exception.ErrorResponse
import com.example.gymi.global.error.exception.BasicException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    @ExceptionHandler(BasicException::class)
    fun globalExceptionHandler(request: HttpServletRequest, ex: BasicException): ResponseEntity<ErrorResponse> {
        log.error(ex.errorCode.message)

        log.error(request.requestURI)

        val errorCode: ErrorCode = ex.errorCode

        return ResponseEntity(
            ErrorResponse(status = errorCode.status, message = errorCode.message),
            HttpStatus.valueOf(errorCode.status)
        )
    }
}