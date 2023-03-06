package com.nazdika.code.challenge.data.datasouce

import com.nazdika.code.challenge.data.datasouce.remote.NazdikaApi
import com.nazdika.code.challenge.model.dto.SoccerMatchResultDto
import retrofit2.Response
import javax.inject.Inject

class LiveScoreRemoteDataSource @Inject constructor(private val api: NazdikaApi) {
    suspend fun getTodayMatches(): Response<SoccerMatchResultDto> {
        return api.getTodayMatches()
    }
}
