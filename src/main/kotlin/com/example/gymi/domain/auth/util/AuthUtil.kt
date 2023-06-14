package com.example.gymi.domain.auth.util

import com.example.gymi.domain.auth.entity.RefreshToken
import com.example.gymi.domain.user.entity.User
import gauth.GAuthUserInfo

interface AuthUtil {
    fun saveNewUser(gAuthUserInfo: GAuthUserInfo, refreshToken: String, token: String?)

    fun saveNewAdmin(gAuthUserInfo: GAuthUserInfo, refreshToken: String, token: String?)

    fun saveNewRefreshToken(userInfo: User, refreshToken: String, token: String?): RefreshToken
}