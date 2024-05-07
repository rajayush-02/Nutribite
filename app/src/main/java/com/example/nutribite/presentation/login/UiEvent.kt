
package com.example.nutribite.presentation.login

sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
}