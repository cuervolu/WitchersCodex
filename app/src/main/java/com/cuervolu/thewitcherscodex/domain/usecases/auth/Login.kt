package com.cuervolu.thewitcherscodex.domain.usecases.auth

import com.cuervolu.thewitcherscodex.domain.repository.FirebaseAuthRepository

class Login(
    private val firebaseAuthRepository: FirebaseAuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Boolean {
        return firebaseAuthRepository.login(username, password)
    }
}