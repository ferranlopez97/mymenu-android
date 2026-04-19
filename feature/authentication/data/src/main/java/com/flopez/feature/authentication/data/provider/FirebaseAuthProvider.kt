package com.flopez.feature.authentication.data.provider

import com.google.firebase.auth.FirebaseAuth
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
}