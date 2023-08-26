package com.example.gymi.domain.notice.presentation.data.request

import javax.validation.constraints.NotBlank

data class EditNoticeRequestDto(
    @NotBlank
    val title: String,
    @NotBlank
    val content: String
)