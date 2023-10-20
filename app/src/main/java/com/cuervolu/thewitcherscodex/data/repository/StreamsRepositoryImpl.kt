package com.cuervolu.thewitcherscodex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cuervolu.thewitcherscodex.data.remote.StreamsPagingSource
import com.cuervolu.thewitcherscodex.data.remote.TwitchApi
import com.cuervolu.thewitcherscodex.domain.model.StreamsData
import com.cuervolu.thewitcherscodex.domain.repository.TwitchRepository
import kotlinx.coroutines.flow.Flow

class StreamsRepositoryImpl(private val twitchApi: TwitchApi) : TwitchRepository {
    override fun getWitcherStreams(): Flow<PagingData<StreamsData>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { StreamsPagingSource(twitchApi) }
        ).flow
    }
}