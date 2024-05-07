

package com.example.nutribite.presentation.cart

import com.example.nutribite.domain.model.CartItem

data class CartState(
    val list: MutableList<CartItem> = mutableListOf()
)


