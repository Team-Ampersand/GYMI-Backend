package com.example.gymi.domain.declaration.enums

enum class DeclarationType(private val description: String) {
    TIME("시간 위반"),
    EQUIPMENT("기자재 위반"),
    SEAT("자리 위반"),
    UNIFORM("복장 위반"),
    FOOD("음식물 반입"),
    DISSENSION("불화"),
    ETC("기타")
}