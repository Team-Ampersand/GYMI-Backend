package com.example.gymi.domain.notice.exception

import com.example.gymi.global.error.exception.BasicException
import com.example.gymi.global.error.exception.ErrorCode

class NoticeNotFoundException : BasicException(ErrorCode.NOTICE_NOT_FOUND) {
}