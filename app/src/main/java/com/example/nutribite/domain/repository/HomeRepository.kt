

package com.example.nutribite.domain.repository

import com.example.nutribite.data.repository.Results
import com.example.nutribite.domain.model.Advertisement
import com.example.nutribite.domain.model.FoodItem
import com.example.nutribite.domain.model.Restaurant

interface HomeRepository {

    suspend fun getRestaurants() : Results<List<Restaurant>>
    suspend fun getAds(): Results<List<Advertisement>>
    suspend fun getFoodItems():Results<List<FoodItem>>
    fun getRestaurantFromName(name: String): Restaurant?
    
}