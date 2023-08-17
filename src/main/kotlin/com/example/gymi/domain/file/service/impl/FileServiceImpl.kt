package com.example.gymi.domain.file.service.impl

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.example.gymi.domain.file.exception.FileUploadFailedException
import com.example.gymi.domain.file.exception.InvalidFormatFileException
import com.example.gymi.domain.file.service.FileService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*

@Service
class FileServiceImpl(
    private val amazonS3: AmazonS3
) : FileService {

    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucket: String

    override fun execute(files: List<MultipartFile>?): List<String> {
        val uploadedUrls = mutableListOf<String>()

        files.orEmpty().forEach { file ->
            val fileName = createFileName(file.originalFilename.toString())
            val objectMetadata = ObjectMetadata()
            objectMetadata.contentLength = file.size
            objectMetadata.contentType = file.contentType

            try {
                file.inputStream.use { inputStream ->
                    amazonS3.putObject(
                        PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
                    )
                }
            } catch (e: IOException) {
                throw FileUploadFailedException()
            }

            val fileUrl = generateFileUrl(fileName)
            uploadedUrls.add(fileUrl)
        }
        return uploadedUrls
    }


    private fun createFileName(fileName: String): String {

        return UUID.randomUUID().toString() + getFileExtension(fileName)
    }

    private fun getFileExtension(fileName: String): String {
        try {
            return fileName.substring(fileName.lastIndexOf("."))
        } catch (e: StringIndexOutOfBoundsException) {
            throw InvalidFormatFileException()
        }
    }

    private fun generateFileUrl(fileName: String): String {

        return amazonS3.getUrl(bucket, fileName).toString()
    }

    fun deleteFile(fileName: String) {
        amazonS3.deleteObject(DeleteObjectRequest(bucket, fileName))
    }
}
