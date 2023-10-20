package com.cuervolu.thewitcherscodex.ui.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.cuervolu.thewitcherscodex.domain.model.StreamsData
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.ExtraSmallPadding
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.ExtraSmallPadding2
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.MediumPadding1
import com.cuervolu.thewitcherscodex.ui.presentation.home.components.StreamCard

@Composable
fun StreamList(
    modifier: Modifier = Modifier,
    streamsData: List<StreamsData>,
    onClick: (StreamsData) -> Unit
) {
    if (streamsData.isEmpty()) {
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(
            count = streamsData.size,
        ) {
            streamsData[it].let { stream ->
                Box(
                    modifier = Modifier.clip(shape = RoundedCornerShape(30.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(), verticalAlignment = Alignment.CenterVertically
                    ) {
                        StreamCard(stream = stream, onClick = { onClick(stream) })
                    }
                }
            }
        }
    }
}

@Composable
fun StreamsList(
    modifier: Modifier = Modifier,
    streams: LazyPagingItems<StreamsData>,
    onClick: (StreamsData) -> Unit
) {

    val handlePagingResult = handlePagingResult(streams)


    if (handlePagingResult) {
        LazyRow(
            modifier = modifier
                .width(406.dp)
                .height(323.dp),
            horizontalArrangement = Arrangement.spacedBy(ExtraSmallPadding),
            contentPadding = PaddingValues(all = ExtraSmallPadding)
        ) {
            items(
                count = streams.itemCount,
            ) {
                streams[it]?.let { stream ->

                    StreamCard(
                        stream = stream,
                        onClick = { onClick(stream) },
                        modifier = modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}


@Composable
fun handlePagingResult(articles: LazyPagingItems<StreamsData>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            StreamCardShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

//@Composable
//fun ShimmerEffect() {
//    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
//        repeat(10) {
//            ArticleCardShimmerEffect(
//                modifier = Modifier.padding(horizontal = MediumPadding1)
//            )
//        }
//    }
//}