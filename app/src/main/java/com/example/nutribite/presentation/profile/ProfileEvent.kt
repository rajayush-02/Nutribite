

package com.example.nutribite.presentation.profile


sealed class ProfileEvent {
    data class PerformLogout(val onClick: () -> Unit) : ProfileEvent()
}