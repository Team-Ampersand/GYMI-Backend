package com.example.gymi.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {

    //SERVER ERROR
    UNKNOWN_ERROR(500, "알 수 없는 에러입니다."),

    //USER
    USER_NOT_FOUND(404, "유저를 찾을 수 없습니다."),
    ROLE_NOT_EXIST(404, "역할이 존재하지 않습니다"),

    //TOKEN
    TOKEN_IS_EXPIRED(401, "토큰이 만료 되었습니다."),
    TOKEN_NOT_VALID(401,"토큰이 유효 하지 않습니다."),

    //FILE
    INVALID_FORMAT_FILE(400,"잘못된 형식의 파일입니다."),
    FILE_UPLOAD_FAIL(500,"파일 업로드에 실패했습니다."),

    //NOTICE
    NOTICE_NOT_FOUND(404, "해당 공지 글을 찾을 수 없습니다.")

}