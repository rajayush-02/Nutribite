

package com.example.nutribite.data.repository

import com.example.nutribite.data.data_source.adList
import com.example.nutribite.data.data_source.recommendedList
import com.example.nutribite.data.data_source.restaurantList
import com.example.nutribite.domain.model.Advertisement
import com.example.nutribite.domain.model.FoodItem
import com.example.nutribite.domain.model.Restaurant
import com.example.nutribite.domain.repository.HomeRepository

class HomeRepositoryImpl() : HomeRepository {

    override suspend fun getRestaurants(): Results<List<Restaurant>> {
        return Results.Success(restaurantList)
    }

    override suspend fun getAds(): Results<List<Advertisement>> {
        return Results.Success(adList)
    }

    override suspend fun getFoodItems(): Results<List<FoodItem>> {
        return Results.Success(recommendedList)
    }

    override fun getRestaurantFromName(name: String): Restaurant? {
        return restaurantList.find {
            it.name == name
        }
    }
}