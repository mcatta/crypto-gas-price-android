package dev.marcocattaneo.push.data.services

import dev.marcocattaneo.gasprice.common.Constants
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

@JvmSuppressWildcards
interface PushService {

    @POST("push")
    fun registerDeviceToken(@Header(Constants.HTTP_HEADER_AUTHORIZATION) bearer: String, @Body body: Map<String, String>)

}