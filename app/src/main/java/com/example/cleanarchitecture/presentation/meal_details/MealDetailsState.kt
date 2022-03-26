package com.example.cleanarchitecture.presentation.meal_details

import com.example.cleanarchitecture.domain.model.Meal
import com.example.cleanarchitecture.domain.model.MealDetails

data class MealDetailsState(
    val data: MealDetails? = null,
    val error: String = "",
    val isLoading: Boolean = false
)