package com.example.gymi.domain.file.util

import com.example.gymi.domain.notice.entity.NoticeFile
import com.example.gymi.domain.notice.repository.NoticeFileRepository
import org.springframework.stereotype.Component

@Component
class FileUtil(

    private val noticeFileRepository: NoticeFileRepository
) {

    fun saveFile(noticeFile: NoticeFile) =
        noticeFileRepository.save(noticeFile)
}