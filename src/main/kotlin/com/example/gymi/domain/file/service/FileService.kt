package com.example.gymi.domain.file.service

import org.springframework.web.multipart.MultipartFile

interface FileService {

    fun execute(multipartFile: List<MultipartFile>?): List<String>
}