package com.flopez.feature.authentication.data.provider

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class SupabaseAuthProvider(
    private val client: SupabaseClient,
) : AuthProvider {

    override suspend fun login(user: String, pwd: String): Boolean {
        client.auth.awaitInitialization()
        client.auth.signInWith(Email) {
            email = user
            password = pwd
        }
        return client.auth.currentUserOrNull()?.email == user
    }

    override suspend fun register(user: String, pwd: String): Boolean {
        client.auth.awaitInitialization()
        client.auth.signUpWith(Email) {
            email = user
            password = pwd
        }
        return client.auth.currentUserOrNull()?.email == user
    }

    override suspend fun logout() {
        client.auth.signOut()
    }

    override fun observeSession(): Flow<Boolean> =
        client.auth.sessionStatus
            .filter { it !is SessionStatus.Initializing }
            .map { it is SessionStatus.Authenticated }
            .distinctUntilChanged()

    override fun observeUserEmail(): Flow<String?> =
        client.auth.sessionStatus
            .filter { it !is SessionStatus.Initializing }
            .map { (it as? SessionStatus.Authenticated)?.session?.user?.email }
            .distinctUntilChanged()
}
