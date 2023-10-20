package com.cuervolu.thewitcherscodex.data.remote

import com.cuervolu.thewitcherscodex.data.remote.dto.WitcherStreamsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitchApi {
    @GET("streams")
    suspend fun getWitcherStreams(
        @Query("game_id") gameId: String = "115977",
        @Query("language") language: String = "en"
    ): WitcherStreamsResponse
}