package com.nazdika.code.challenge.data.datasouce.remote

import com.nazdika.code.challenge.model.dto.SoccerMatchResultDto
import retrofit2.Response
import retrofit2.http.GET

interface NazdikaApi {
    @GET("foot/match/live")
    suspend fun getTodayMatches(): Response<SoccerMatchResultDto>
}