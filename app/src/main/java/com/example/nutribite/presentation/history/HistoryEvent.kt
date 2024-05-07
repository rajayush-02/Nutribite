

package com.example.nutribite.presentation.history

import com.example.nutribite.domain.model.Restaurant

sealed class HistoryEvent {
    data class SelectRestaurant(val restaurant: Restaurant, val onClick: () -> Unit) :
        HistoryEvent()
}