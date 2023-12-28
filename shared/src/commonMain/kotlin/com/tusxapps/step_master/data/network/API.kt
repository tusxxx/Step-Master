package com.tusxapps.step_master.data.network

import com.tusxapps.step_master.data.network.models.CodeResponse
import com.tusxapps.step_master.data.network.models.DayResponse
import com.tusxapps.step_master.data.network.models.DaysResponse
import com.tusxapps.step_master.data.network.models.ProfileResponse
import com.tusxapps.step_master.data.network.models.RegionsResponse
import com.tusxapps.step_master.domain.exceptions.DayExistsException
import com.tusxapps.step_master.domain.exceptions.WrongPasswordException
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.takeFrom
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import io.ktor.http.parameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

private const val AUTH_PATH = "Authorization"
private const val PROFILE_PATH = "Profile"
private const val REGIONS_PATH = "Regions"
private const val DAYS_PATH = "Days"
private const val BASE_URL = "http://93.190.106.199:80/api"

class API(
    private val httpClient: HttpClient
) {

    suspend fun login(email: String, password: String) = withContext(Dispatchers.IO) {
        httpClient.plugin(Auth).basic {
            credentials {
                BasicAuthCredentials(email, password)
            }
            sendWithoutRequest { true }
        }
        val loginResponse = httpClient.get {
            url("$BASE_URL/$AUTH_PATH/Auth")
        }

        loginResponse
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
    ): ProfileResponse = withContext(Dispatchers.IO) {
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

    suspend fun recoverPassword(email: String) = withContext(Dispatchers.IO) {
        httpClient.put {
            url("$BASE_URL/$AUTH_PATH/RecoveryPassword")
            setBody(
                FormDataContent(parameters {
                    append("email", email)
                })
            )
        }
    }

    // Days API
    suspend fun getDays(): DaysResponse = withContext(Dispatchers.IO) {
        httpClient.get("$BASE_URL/$DAYS_PATH/GetAllDayUser").refreshCookiesOnUnauthorized().body()
    }

    suspend fun uploadDay(
        calories: Float,
        steps: Int,
        distance: Float,
        planDistance: Float,
        planSteps: Int,
        planCalories: Float,
        date: String,
    ): DayResponse = withContext(Dispatchers.IO) {
        val response = httpClient.submitForm(
            url = "$BASE_URL/$DAYS_PATH/SetNewDay",
            formParameters = parameters {
                append("calories", calories.toString())
                append("steps", steps.toString())
                append("distance", distance.toString())
                append("plandistance", planDistance.toString())
                append("plansteps", planSteps.toString())
                append("plancalories", planCalories.toString())
                append("date", date)
            }
        ).refreshCookiesOnUnauthorized()
        if (response.status == HttpStatusCode.Conflict) {
            throw DayExistsException()
        }
        response.body()
    }

    suspend fun editDay(
        id: String,
        calories: Float,
        steps: Int,
        distance: Float,
        planDistance: Float,
        planSteps: Int,
        planCalories: Float,
        date: String,
    ): DayResponse = withContext(Dispatchers.IO) {
        httpClient.put {
            url("$BASE_URL/$DAYS_PATH/UploadDay")
            setBody(
                FormDataContent(parameters {
                    append("_id", id)
                    append("calories", calories.toString())
                    append("steps", steps.toString())
                    append("distance", distance.toString())
                    append("plandistance", planDistance.toString())
                    append("plansteps", planSteps.toString())
                    append("plancalories", planCalories.toString())
                    append("date", date)
                })
            )
        }.refreshCookiesOnUnauthorized().body()
    }

    private suspend fun HttpResponse.refreshCookiesOnUnauthorized(): HttpResponse {
        val response = this
        return withContext(Dispatchers.IO) {
            if (status == HttpStatusCode.Unauthorized || status == HttpStatusCode.Forbidden) {
                Napier.d(tag = "Cookies", message = "Refresh cookies")
                httpClient.get("$BASE_URL/$AUTH_PATH/UpdateCookies")
                httpClient.request(HttpRequestBuilder().takeFrom(response.request))
            } else {
                response
            }
        }
    }


    // Profile API
    suspend fun getProfile(): ProfileResponse = withContext(Dispatchers.IO) {
        httpClient
            .get("$BASE_URL/$PROFILE_PATH/GetUser")
            .refreshCookiesOnUnauthorized()
            .body()
    }

    suspend fun editProfile(
        fullName: String? = null,
        regionId: String? = null,
        nickname: String? = null
    ): ProfileResponse = withContext(Dispatchers.IO) {
        httpClient
            .put("$BASE_URL/$PROFILE_PATH/EditUser") {
                setBody(
                    FormDataContent(
                        parameters {
                            fullName?.let { append("fullName", it) }
                            regionId?.let { append("region_id", it) }
                            nickname?.let { append("nickname", it) }
                        }
                    )
                )
            }
            .refreshCookiesOnUnauthorized()
            .body()
    }

    suspend fun uploadAvatar(image: ByteArray) {
        withContext(Dispatchers.IO) {
            httpClient
                .submitFormWithBinaryData(
                    url = "$BASE_URL/$PROFILE_PATH/InsertAvatar",
                    formData = formData {
                        append(
                            key = "image",
                            value = image,
                            headers = headers {
                                append(HttpHeaders.ContentType, "image/png")
                                append(HttpHeaders.ContentDisposition, "filename=\"avatar.png\"")
                            }
                        )
                    }
                )
                .refreshCookiesOnUnauthorized()
        }
    }

    suspend fun downloadAvatar(link: String): ByteArray = withContext(Dispatchers.IO) {
        httpClient.get(urlString = link).body()
    }

    suspend fun editPassword(
        oldPassword: String,
        newPassword: String
    ) = withContext(Dispatchers.IO) {
        val response = httpClient.put("$BASE_URL/$PROFILE_PATH/EditPassword") {
            setBody(
                FormDataContent(
                    parameters {
                        append("oldPassword", oldPassword)
                        append("newPassword", newPassword)
                    }
                )
            )
        }
        if (response.status == HttpStatusCode.Forbidden) {
            throw WrongPasswordException("Wrong password")
        }
        response
    }

}
