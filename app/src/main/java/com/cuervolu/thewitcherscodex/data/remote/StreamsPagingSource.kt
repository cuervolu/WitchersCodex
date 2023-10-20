package com.cuervolu.thewitcherscodex.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cuervolu.thewitcherscodex.domain.model.StreamsData
import com.google.android.gms.common.api.ApiException
import timber.log.Timber

class StreamsPagingSource(
    private val twitchApi: TwitchApi
) : PagingSource<Int, StreamsData>() {
    private val totalStreamsCount = 0

    override fun getRefreshKey(state: PagingState<Int, StreamsData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StreamsData> {
        val page = params.key ?: 1
        return try {
            val response = twitchApi.getWitcherStreams()
            val streams = response.data
            LoadResult.Page(
                data = streams,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (streams.isEmpty()) null else page + 1
            )
        } catch (e: ApiException) {
            Timber.e(e.localizedMessage)
            LoadResult.Error(e)
        }
    }

}