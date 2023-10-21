package com.cuervolu.thewitcherscodex.data.repository

import com.cuervolu.thewitcherscodex.domain.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class FirebaseAuthRepositoryImpl() : FirebaseAuthRepository {
    private val auth = FirebaseAuth.getInstance()
    override suspend fun login(username: String, password: String): Boolean {
        return try {
            val result = auth.signInWithEmailAndPassword(username, password).await()
            result.user != null
        } catch (e: FirebaseAuthException) {
            Timber.e(e.localizedMessage)
            false
        }
    }
}