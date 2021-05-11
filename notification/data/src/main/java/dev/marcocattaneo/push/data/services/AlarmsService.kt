package dev.marcocattaneo.push.data.services

import dev.marcocattaneo.gasprice.common.Constants
import dev.marcocattaneo.push.data.models.Alarm
import retrofit2.http.*

interface AlarmsService {

    @GET("alarms")
    suspend fun getAll(@Header(Constants.HTTP_HEADER_AUTHORIZATION) bearer: String): List<Alarm>

    @POST("alarms")
    suspend fun create(@Header(Constants.HTTP_HEADER_AUTHORIZATION) bearer: String, @Body body: Map<String, Any>): Alarm

    @DELETE("alarms/{id}")
    suspend fun delete(@Header(Constants.HTTP_HEADER_AUTHORIZATION) bearer: String, @Path("id") id: String)

}