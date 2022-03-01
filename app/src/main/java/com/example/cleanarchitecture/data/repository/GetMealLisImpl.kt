package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.model.MealsDTO
import com.example.cleanarchitecture.data.remote.MealSearchAPI
import com.example.cleanarchitecture.domain.repository.MealSearchRepository

class GetMealLisImpl(private val mealSearchAPI: MealSearchAPI) : MealSearchRepository {
    override suspend fun getMealList(s: String): MealsDTO {
        return mealSearchAPI.getMealList(s)
    }
}