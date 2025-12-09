package com.example.android108

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class TokenStorage(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun setTokens(accessToken: String?, refreshToken: String?) {
        prefs.edit {
            putString(ACCESS_TOKEN, accessToken)
            putString(REFRESH_TOKEN, refreshToken)
        }
    }

    fun getAccessToken(): String? {
        return prefs.getString(ACCESS_TOKEN, null)
    }

    fun getRefreshToken(): String? {
        return prefs.getString(REFRESH_TOKEN, null)
    }

    fun clearTokens() {
        prefs.edit {
            remove(ACCESS_TOKEN)
            remove(REFRESH_TOKEN)
        }
    }
}