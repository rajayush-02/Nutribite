

package com.example.nutribite.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutribite.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {


    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.PerformLogout -> {
              viewModelScope.launch()  {
                    repository.toggleLoginState()
                    event.onClick()
                }
            }
        }
    }
}