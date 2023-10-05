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
    TOKEN_NOT_VALID(401, "토큰이 유효 하지 않습니다."),

    //FILE
    INVALID_FORMAT_FILE(400, "잘못된 형식의 파일입니다."),
    FILE_UPLOAD_FAIL(500, "파일 업로드에 실패했습니다."),

    //NOTICE
    NOTICE_NOT_FOUND(404, "해당 공지 글을 찾을 수 없습니다."),

    //RESERVATION
    COURT_RESERVATION_DAY_IS_NOT_VALID(401, "토요일 혹은 일요일에는 체육관을 신청할 수 없습니다."),
    COURT_RESERVATION_HOUR_OR_MINUTE_IS_NOT_VALID(401, "체육관 신청은 오전 11시 30분부터 오전 11시 59분까지 혹은 오후 5시 30분부터 오후 5시 59분까지만 가능합니다."),
    COURT_RESERVATION_CANCEL_DAY_IS_NOT_VALID(401, "토요일 혹은 일요일에는 체육관을 신청 취소 할 수 없습니다."),
    COURT_RESERVATION_CANCEL_HOUR_OR_MINUTE_IS_NOT_VALID(401, "체육관 신청 취소는 오전 11시 30분부터 오전 11시 59분까지 혹은 오후 5시 30분부터 오후 5시 59분까지만 가능합니다."),
    COURT_RESERVATION_NOT_FOUND(404, "코트 예약 내역이 존재하지 않습니다."),
    COURT_RESERVATION_OVER(409, "해당 코트의 최대 인원을 초과했습니다."),
    COURT_RESERVATION_CANT_CANCEL(409, "현재 신청 취소를 할 수 있는 상태가 아닙니다."),
    ALREADY_RESERVATION_COURT_IS_EXISTS(409, "이미 예약한 코트가 존재합니다.")
}