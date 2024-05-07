
package com.example.nutribite.domain.model

data class MenuItem(
    val dish: String,
    val price: Double,
    val rating: Double,
    val noOfRatings: Int,
    val isVegetarian: Boolean,
    val isDiabetic : Boolean,
    val isDairyAllergic : Boolean,
    val isHighCholesterol : Boolean,
    val isCeliacDisease : Boolean,
    val isHypertension : Boolean,
    val isBloodPressure : Boolean,

)
