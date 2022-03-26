package com.example.cleanarchitecture.presentation.meal_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.domain.use_cases.GetMealDetailsUseCases
import com.example.cleanarchitecture.presentation.meal_search.MealSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(private val getMealDetailsUseCases: GetMealDetailsUseCases) :
    ViewModel() {

    private val _mealDetails = MutableStateFlow(MealDetailsState())
    var mealDetails: StateFlow<MealDetailsState> = _mealDetails

    fun getMealDetails(id: String) {
        getMealDetailsUseCases(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealDetails.value = MealDetailsState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealDetails.value = MealDetailsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _mealDetails.value = MealDetailsState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}