package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.data.model.MealsDTO

interface MealSearchRepository {
    suspend fun getMealList(s:String):MealsDTO
}