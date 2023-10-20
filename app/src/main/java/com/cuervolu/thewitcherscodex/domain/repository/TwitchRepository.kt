package com.cuervolu.thewitcherscodex.domain.repository

import androidx.paging.PagingData
import com.cuervolu.thewitcherscodex.domain.model.StreamsData
import kotlinx.coroutines.flow.Flow

interface TwitchRepository {
    fun getWitcherStreams(): Flow<PagingData<StreamsData>>
}