package com.cuervolu.thewitcherscodex.domain.usecases.characters

import androidx.paging.PagingData
import com.cuervolu.thewitcherscodex.domain.model.Character
import com.cuervolu.thewitcherscodex.domain.repository.FirestoreRepository
import kotlinx.coroutines.flow.Flow

class GetCharacters(private val firestoreRepository: FirestoreRepository) {
    operator fun invoke(): Flow<PagingData<Character>> {
        return firestoreRepository.getCharacters()
    }
}