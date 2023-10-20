package com.cuervolu.thewitcherscodex.domain.repository

import androidx.paging.PagingData
import com.cuervolu.thewitcherscodex.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {

    fun getCharacters(): Flow<PagingData<Character>>
}