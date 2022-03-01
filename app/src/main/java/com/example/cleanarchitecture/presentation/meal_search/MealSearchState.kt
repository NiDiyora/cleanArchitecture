package com.example.cleanarchitecture.presentation.meal_search

import com.example.cleanarchitecture.domain.model.Meal

data class MealSearchState(
    val data: List<Meal>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)
