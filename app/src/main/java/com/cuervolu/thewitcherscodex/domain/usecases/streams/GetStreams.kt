package com.cuervolu.thewitcherscodex.domain.usecases.streams

import androidx.paging.PagingData
import com.cuervolu.thewitcherscodex.domain.model.StreamsData
import com.cuervolu.thewitcherscodex.domain.repository.TwitchRepository
import kotlinx.coroutines.flow.Flow

class GetStreams(private val streamsRepository: TwitchRepository) {
    operator fun invoke(): Flow<PagingData<StreamsData>> {
        return streamsRepository.getWitcherStreams()
    }
}