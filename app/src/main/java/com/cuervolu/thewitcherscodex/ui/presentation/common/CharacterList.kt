package com.cuervolu.thewitcherscodex.ui.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.cuervolu.thewitcherscodex.domain.model.Character
import com.cuervolu.thewitcherscodex.ui.presentation.characters.components.CharacterCard

@Composable
fun CharactersList(
    modifier: Modifier = Modifier,
    characters: LazyPagingItems<Character>,
    onClick: (Character) -> Unit
) {
    val lazyListState = rememberLazyGridState()
    val handlePagingResult = handlePagingResult(characters)

    if (handlePagingResult) {
        LazyVerticalGrid(
            modifier = modifier,
            state = lazyListState,
            content = {
                items(characters.itemCount) { character ->
                    CharacterCard(
                        character = characters[character]!!,
                        onClick = { onClick(characters[character]!!) },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            },
            columns = GridCells.Fixed(2)
        )
    }
}

@Composable
fun handlePagingResult(articles: LazyPagingItems<Character>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            CharacterShimmerEffect()
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