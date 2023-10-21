package com.cuervolu.thewitcherscodex.domain.repository

interface FirebaseAuthRepository {
    suspend fun login(username: String, password: String): Boolean
}