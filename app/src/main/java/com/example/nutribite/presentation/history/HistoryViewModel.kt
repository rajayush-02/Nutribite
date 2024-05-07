

package com.example.nutribite.presentation.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutribite.domain.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    private val _likedRestaurants = mutableStateOf(HistoryState())
    val likedRestaurants: State<HistoryState> = _likedRestaurants

    init {
        viewModelScope.launch {
            userDataRepository.getLikedRestaurants().collect {
                _likedRestaurants.value = likedRestaurants.value.copy(
                    likedRestaurantList = it
                )

            }

        }
    }

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.SelectRestaurant -> {
                viewModelScope.launch {
                    userDataRepository.setRestaurant(event.restaurant)
                    event.onClick()
                }
            }
        }
    }

}