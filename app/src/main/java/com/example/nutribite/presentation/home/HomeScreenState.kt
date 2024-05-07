

package com.example.nutribite.presentation.home

import com.example.nutribite.domain.model.Advertisement
import com.example.nutribite.domain.model.FoodItem
import com.example.nutribite.domain.model.Restaurant

data class HomeScreenState(
    val adsList: List<Advertisement> = emptyList(),
    val foodList: List<FoodItem> = emptyList(),
    val likedRestaurantList : List<Restaurant> = emptyList(),
    val restaurantList : List<Restaurant> = emptyList(),
)
