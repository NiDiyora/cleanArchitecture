package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.model.MealsDTO
import com.example.cleanarchitecture.data.remote.MealSearchAPI
import com.example.cleanarchitecture.domain.repository.GetMealDetailsRepository

class GetMealDetailsImpl(private val mealSearchAPI: MealSearchAPI) : GetMealDetailsRepository {
    override suspend fun getMealDetails(id: String): MealsDTO {
        return mealSearchAPI.getMealDetails(id)
    }
}