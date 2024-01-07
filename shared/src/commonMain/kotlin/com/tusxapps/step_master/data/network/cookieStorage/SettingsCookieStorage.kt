package com.tusxapps.step_master.data.network.cookieStorage

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.http.Cookie
import io.ktor.http.Url

class SettingsCookieStorage(private val settings: Settings) : CookiesStorage {
    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        settings[requestUrl.host+"name"] = cookie.name
        settings[requestUrl.host+"value"] = cookie.value
    }

    override suspend fun get(requestUrl: Url): List<Cookie> {
        val cookieName: String? = settings.getStringOrNull(requestUrl.host+"name")
        val cookieValue: String? = settings.getStringOrNull(requestUrl.host+"value")
        return if (cookieName != null && cookieValue != null) {
            listOf(Cookie(cookieName, cookieValue))
        } else {
            emptyList()
        }
    }

    override fun close() {

    }
}