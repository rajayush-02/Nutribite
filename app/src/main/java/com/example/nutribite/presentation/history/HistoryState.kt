

package com.example.nutribite.presentation.history

import com.example.nutribite.domain.model.Restaurant

data class HistoryState(
    val likedRestaurantList: List<Restaurant> = emptyList(),
)
