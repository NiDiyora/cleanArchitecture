package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.data.model.MealsDTO

interface GetMealDetailsRepository {
    suspend fun getMealDetails(id:String):MealsDTO
}