package com.example.cleanarchitecture.presentation.meal_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.domain.use_cases.GetMealSearchListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(private val getMealSearchListUseCases: GetMealSearchListUseCases) :
    ViewModel() {

    private val _mealSearchList = MutableStateFlow<MealSearchState>(MealSearchState())
    val mealSearchList: StateFlow<MealSearchState> = _mealSearchList

    fun searchMealList(s: String) {
        getMealSearchListUseCases(s).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealSearchList.value = MealSearchState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealSearchList.value = MealSearchState(error = it.message ?: "")

                }
                is Resource.Success -> {
                    _mealSearchList.value = MealSearchState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}