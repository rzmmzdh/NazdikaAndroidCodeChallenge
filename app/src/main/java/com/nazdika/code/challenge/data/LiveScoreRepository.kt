package com.nazdika.code.challenge.data

import com.nazdika.code.challenge.data.datasouce.LiveScoreRemoteDataSource
import com.nazdika.code.challenge.data.datasouce.Result
import com.nazdika.code.challenge.model.CompetitionMatch
import com.nazdika.code.challenge.model.dto.CompetitionMatchDto
import com.nazdika.code.challenge.model.dto.asCompetitionMatch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LiveScoreRepository @Inject constructor(private val liveScoreRemoteDataSource: LiveScoreRemoteDataSource) {
    suspend fun getTodayMatches(): Flow<Result<List<CompetitionMatch>>> =
        flow {
            val result = liveScoreRemoteDataSource.getTodayMatches()
            emit(Result.Loading)
            when {
                result.isSuccessful -> {
                    emit(
                        Result.Success(
                            data = result.body()?.data?.competitionMatches?.map {
                                it.asCompetitionMatch()
                            }
                                ?: emptyList()
                        )
                    )
                }

                else -> {
                    emit(
                        Result.Error(
                            exception = Throwable(
                                message = "An error occurred: ${result.code()}"
                            )
                        )
                    )
                }
            }
        }
}