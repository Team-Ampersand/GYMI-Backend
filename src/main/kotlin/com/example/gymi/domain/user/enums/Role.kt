package com.example.gymi.domain.user.enums

import org.springframework.security.core.GrantedAuthority

enum class Role(description: String) : GrantedAuthority {

    ROLE_STUDENT("학생"), ROLE_ADMIN("관리자");

    override fun getAuthority(): String = name
}