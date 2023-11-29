package com.tusxapps.step_master.data.network

import com.tusxapps.step_master.data.network.models.CodeResponse
import com.tusxapps.step_master.data.network.models.DayResponse
import com.tusxapps.step_master.data.network.models.DaysResponse
import com.tusxapps.step_master.data.network.models.LoginResponse
import com.tusxapps.step_master.data.network.models.RegionsResponse
import com.tusxapps.step_master.data.network.models.RegistrationResponse
import com.tusxapps.step_master.domain.exceptions.DayExistsException
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.takeFrom
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.http.HttpStatusCode
import io.ktor.http.parameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

private const val AUTH_PATH = "Authorization"
private const val REGIONS_PATH = "Regions"
private const val DAYS_PATH = "Days"
private const val BASE_URL = "http://93.190.106.199:5000/api"

class API(
    private val httpClient: HttpClient
) {
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
}
