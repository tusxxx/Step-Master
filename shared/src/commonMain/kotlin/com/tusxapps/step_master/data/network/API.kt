package com.tusxapps.step_master.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.plugin
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse

private const val AUTH_PATH = "Authentication"
private const val BASE_URL = "http://237.84.2.178"

class API(private val httpClient: HttpClient) {
    suspend fun login(email: String, password: String): HttpResponse {
        httpClient.plugin(Auth).basic {
            BasicAuthCredentials(email, password)
        }
        return httpClient.get {
            url("$BASE_URL/$AUTH_PATH/Auth")
        }
    }
}