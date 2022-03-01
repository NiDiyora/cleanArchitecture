package com.example.cleanarchitecture.domain.use_cases

import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.data.model.MealsDTO
import com.example.cleanarchitecture.data.model.toDomainMealDetails
import com.example.cleanarchitecture.domain.model.MealDetails
import com.example.cleanarchitecture.domain.repository.GetMealDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetMealDetailsUseCases @Inject constructor(private val repository: GetMealDetailsRepository) {

    operator fun invoke(id: String): Flow<Resource<MealDetails>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.getMealDetails(id).meals[0].toDomainMealDetails()

            // val details =
            //   if (response.meals.isNullOrEmpty()) emptyList<MealDetails>() else response.meals.map { it.toDomainMealDetails() }

            emit(Resource.Success(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))

        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Your Internet Connection"))

        } catch (e: Exception) {
        }
    }
}