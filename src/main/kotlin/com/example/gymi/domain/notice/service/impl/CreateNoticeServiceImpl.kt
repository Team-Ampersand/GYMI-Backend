package com.example.gymi.domain.notice.service.impl

import com.example.gymi.domain.notice.entity.NoticeFile
import com.example.gymi.domain.file.service.FileService
import com.example.gymi.domain.file.util.FileUtil
import com.example.gymi.domain.notice.entity.Notice
import com.example.gymi.domain.notice.presentation.data.dto.CreateNoticeDto
import com.example.gymi.domain.notice.presentation.data.request.CreateNoticeRequestDto
import com.example.gymi.domain.notice.service.CreateNoticeService
import com.example.gymi.domain.notice.util.NoticeUtil
import com.example.gymi.domain.user.entity.User
import com.example.gymi.global.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional(rollbackFor = [Exception::class])
class CreateNoticeServiceImpl(
    private val userUtil: UserUtil,
    private val fileUtil: FileUtil,
    private val noticeUtil: NoticeUtil,
    private val fileService: FileService
) : CreateNoticeService {

    override fun execute(createNoticeRequestDto: CreateNoticeRequestDto, multipartFile: List<MultipartFile>?): Notice {

        val user: User = userUtil.currentUser()

        val createNoticeDto: CreateNoticeDto = toDto(createNoticeRequestDto = createNoticeRequestDto)

        if (multipartFile == null) {
            return toEntity(createNoticeDto, user)
                .let { noticeUtil.saveNotice(notice = it) }
        }

        val notice: Notice = toEntity(createNoticeDto, user)
            .let { noticeUtil.saveNotice(notice = it) }

        val uploadFile: List<String> = fileService.execute(multipartFile)
        for (uploadFileUrl: String in uploadFile) {
            toEntity(notice = notice, uploadFileUrl)
                .let { fileUtil.saveFile(noticeFile = it) }
        }

        return notice
    }

    private fun toEntity(createNoticeDto: CreateNoticeDto, user: User): Notice =
        Notice(
            title = createNoticeDto.title,
            content = createNoticeDto.content,
            user = user,
            noticeFile = listOf()
        )

    private fun toEntity(notice: Notice, uploadFileUrl: String): NoticeFile =
        NoticeFile(
            notice = notice,
            url = uploadFileUrl
        )

    private fun toDto(createNoticeRequestDto: CreateNoticeRequestDto): CreateNoticeDto =
        CreateNoticeDto(
            title = createNoticeRequestDto.title,
            content = createNoticeRequestDto.content
        )
}