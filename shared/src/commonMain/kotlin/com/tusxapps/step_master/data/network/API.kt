package com.tusxapps.step_master.data.network

import com.tusxapps.step_master.data.network.models.CodeResponse
import com.tusxapps.step_master.data.network.models.LoginResponse
import com.tusxapps.step_master.data.network.models.RegionsResponse
import com.tusxapps.step_master.data.network.models.RegistrationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.plugin
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.parameters
import io.ktor.http.setCookie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

private const val AUTH_PATH = "Authorization"
private const val REGIONS_PATH = "Regions"
private const val BASE_URL = "http://158.160.77.82:5000/api"

class API(
    private val httpClient: HttpClient
) {
    private var cookie: String? = null

    suspend fun login(email: String, password: String): LoginResponse =
        withContext(Dispatchers.IO) {
            httpClient.plugin(Auth).basic {
                credentials {
                    BasicAuthCredentials(email, password)
                }
                sendWithoutRequest { true }
            }
            val loginResponse = httpClient.get {
                url("$BASE_URL/$AUTH_PATH/Auth")
            }

            loginResponse.setCookie().firstOrNull()?.let {
                cookie = it.name + "=" + it.value
            }

            loginResponse.body()
        }

    suspend fun sendCodeToUser(email: String): CodeResponse = withContext(Dispatchers.IO) {
        val response = httpClient.submitForm(
            url = "$BASE_URL/$AUTH_PATH/SendCode",
            formParameters = parameters {
                append("email", email)
            }
        )
        response.body()
    }

    suspend fun register(
        email: String,
        nickname: String,
        fullName: String,
        regionId: String,
        gender: String,
        password: String,
    ): RegistrationResponse = withContext(Dispatchers.IO) {
        httpClient.submitForm(
            url = "$BASE_URL/$AUTH_PATH/Registration",
            formParameters = parameters {
                append("email", email)
                append("nickname", nickname)
                append("fullName", fullName)
                append("region_id", regionId)
                append("gender", gender)
                append("password", password)
            }
        ).body()
    }

    suspend fun getRegions(): RegionsResponse = withContext(Dispatchers.IO) {
        httpClient.get("$BASE_URL/$REGIONS_PATH/GetRegions").body()
    }
}