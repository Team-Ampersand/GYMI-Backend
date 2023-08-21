package com.example.gymi.domain.notice.service.impl

import com.example.gymi.domain.file.service.FileService
import com.example.gymi.domain.notice.entity.Notice
import com.example.gymi.domain.notice.entity.NoticeFile
import com.example.gymi.domain.notice.exception.NoticeNotFoundException
import com.example.gymi.domain.notice.repository.NoticeFileRepository
import com.example.gymi.domain.notice.repository.NoticeRepository
import com.example.gymi.domain.notice.service.DeleteNoticeService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class DeleteNoticeServiceImpl(

    private val noticeRepository: NoticeRepository,
    private val noticeFileRepository: NoticeFileRepository,
    private val fileService: FileService
) : DeleteNoticeService {

    override fun execute(id: Long) {

        val noticeInfo: Notice = noticeRepository.findByIdOrNull(id)
            ?: throw NoticeNotFoundException()

        val noticeFiles: List<NoticeFile> = noticeFileRepository.findAllByNoticeId(id)

        if (noticeFiles.isEmpty()) {
            noticeRepository.delete(noticeInfo)
        } else {
            for (noticeFile in noticeFiles) {
                fileService.deleteFile(noticeFile.url.substring(54))
                noticeFileRepository.delete(noticeFile)
            }
            noticeRepository.delete(noticeInfo)
        }
    }
}