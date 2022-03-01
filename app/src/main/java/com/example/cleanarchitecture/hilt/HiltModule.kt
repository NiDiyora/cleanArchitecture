package com.example.cleanarchitecture.hilt

import com.example.cleanarchitecture.common.Constant
import com.example.cleanarchitecture.data.remote.MealSearchAPI
import com.example.cleanarchitecture.data.repository.GetMealDetailsImpl
import com.example.cleanarchitecture.data.repository.GetMealLisImpl
import com.example.cleanarchitecture.domain.repository.GetMealDetailsRepository
import com.example.cleanarchitecture.domain.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    @Singleton
    fun ProvideMealSearchAPI(): MealSearchAPI {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MealSearchAPI::class.java)
    }

    @Provides
    fun provideMealSearchRepository(mealSearchAPI: MealSearchAPI): MealSearchRepository {
        return GetMealLisImpl(mealSearchAPI)
    }

    @Provides
    fun provideMealDetailsRepository(mealSearchAPI: MealSearchAPI): GetMealDetailsRepository {
        return GetMealDetailsImpl(mealSearchAPI)
    }
}