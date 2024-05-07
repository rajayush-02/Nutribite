
package com.example.nutribite.domain.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    //    fun readLoginState(): Boolean
    suspend fun toggleLoginState()
    val loginState: Flow<Boolean>

}