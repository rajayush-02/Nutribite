

package com.example.nutribite.presentation.home

import com.example.nutribite.domain.model.Restaurant

sealed class HomeScreenEvent {
    data class SelectRestaurant(val restaurant: Restaurant, val onClick: () -> Unit) :
        HomeScreenEvent()
}