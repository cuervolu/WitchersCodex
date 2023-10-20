package com.cuervolu.thewitcherscodex.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cuervolu.thewitcherscodex.domain.model.Character
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class CharacterPagingSource(private val firestore: FirebaseFirestore) :
    PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1
        var totalItems = 0
        return try {
            val characterCollection = firestore.collection("characters")
            val query = characterCollection.limit(params.loadSize.toLong()).orderBy("name")
            totalItems += characterCollection.get().await().size()
            val snapshot = if (page == 1) {
                query.get().await()
            } else {
                val lastCharacter = params.key ?: return LoadResult.Page(emptyList(), null, null)
                query.startAfter(lastCharacter).get().await()
            }

            val characters = snapshot.toObjects(Character::class.java).distinctBy { it.name }

            LoadResult.Page(
                data = characters,
                nextKey = if (totalItems == characterCollection.get().await()
                        .size()
                ) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
            LoadResult.Error(e)
        }
    }
}