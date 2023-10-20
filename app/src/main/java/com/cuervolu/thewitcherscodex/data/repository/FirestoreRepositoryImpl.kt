package com.cuervolu.thewitcherscodex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cuervolu.thewitcherscodex.data.remote.CharacterPagingSource
import com.cuervolu.thewitcherscodex.domain.model.Character
import com.cuervolu.thewitcherscodex.domain.repository.FirestoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

class FirestoreRepositoryImpl() : FirestoreRepository {
    private val db = FirebaseFirestore.getInstance()
    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { CharacterPagingSource(db) }
        ).flow
    }
}