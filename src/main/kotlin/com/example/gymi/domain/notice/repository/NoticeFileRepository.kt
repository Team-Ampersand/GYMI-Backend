package com.example.gymi.domain.notice.repository

import com.example.gymi.domain.notice.entity.NoticeFile
import org.springframework.data.jpa.repository.JpaRepository

interface NoticeFileRepository : JpaRepository<NoticeFile, Long> {

    fun findAllByNoticeId(id: Long): List<NoticeFile>
}