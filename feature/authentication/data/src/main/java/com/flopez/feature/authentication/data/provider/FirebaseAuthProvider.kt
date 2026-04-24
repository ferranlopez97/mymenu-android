package com.flopez.feature.authentication.data.provider

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class FirebaseAuthProvider(
    private val firebaseAuth: FirebaseAuth,
) : AuthProvider {

    override suspend fun login(user: String, pwd: String): Boolean {
        val result = firebaseAuth.signInWithEmailAndPassword(user, pwd).await()
        return result.user != null
    }

    override suspend fun register(user: String, pwd: String): Boolean {
        val result = firebaseAuth.createUserWithEmailAndPassword(user, pwd).await()
        return result.user != null
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    private fun authStateFlow(): Flow<FirebaseAuth> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth -> trySend(auth) }
        firebaseAuth.addAuthStateListener(listener)
        awaitClose { firebaseAuth.removeAuthStateListener(listener) }
    }

    override fun observeSession(): Flow<Boolean> =
        authStateFlow().map { it.currentUser != null }.distinctUntilChanged()

    override fun observeUserEmail(): Flow<String?> =
        authStateFlow().map { it.currentUser?.email }.distinctUntilChanged()
}
