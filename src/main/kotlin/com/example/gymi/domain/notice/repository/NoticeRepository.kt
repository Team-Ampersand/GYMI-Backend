package com.example.gymi.domain.notice.repository

import com.example.gymi.domain.notice.entity.Notice
import org.springframework.data.jpa.repository.JpaRepository

interface NoticeRepository : JpaRepository<Notice, Long> {
}