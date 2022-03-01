package com.example.cleanarchitecture.domain.use_cases

import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.data.model.toDomainMeal
import com.example.cleanarchitecture.domain.model.Meal
import com.example.cleanarchitecture.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetMealSearchListUseCases @Inject constructor(private val repository: MealSearchRepository) {
    operator fun invoke(s: String): Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.getMealList(s)
            val list =
                if (response.meals.isNullOrEmpty()) emptyList<Meal>() else response.meals.map { it.toDomainMeal() }

            emit(Resource.Success(data = list))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Your Internet Connection"))

        } catch (e: Exception) {

        }
    }
}