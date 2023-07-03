package com.example.gymi.global.security.filter

import com.example.gymi.global.error.exception.ErrorCode
import com.example.gymi.global.error.exception.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        try {
            filterChain.doFilter(request, response)
        } catch (ex: ExpiredJwtException) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 ExpiredJwtException 발생 ===================")
            setErrorResponse(ErrorCode.TOKEN_IS_EXPIRED, response)
        } catch (ex: JwtException) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 JwtException 발생 ===================")
            setErrorResponse(ErrorCode.TOKEN_NOT_VALID, response)
         } catch (ex: IllegalArgumentException) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 JwtException 발생 ===================")
            setErrorResponse(ErrorCode.TOKEN_NOT_VALID, response)
        } catch (ex: Exception) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 Exception 발생 ===================")
            setErrorResponse(ErrorCode.UNKNOWN_ERROR, response)
        }
    }

    private fun setErrorResponse(errorCode: ErrorCode, response: HttpServletResponse) {
        val errorResponse = ErrorResponse(errorCode.status, errorCode.message)
        val responseString = objectMapper!!.writeValueAsString(errorResponse)
        response.status = errorCode.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(responseString)
    }
}