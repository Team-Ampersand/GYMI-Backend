package com.example.gymi.domain.notice.presentation.data.request

import javax.validation.constraints.NotBlank

data class CreateNoticeRequestDto(
    @NotBlank
    val title: String,
    @NotBlank
    val content: String
)
