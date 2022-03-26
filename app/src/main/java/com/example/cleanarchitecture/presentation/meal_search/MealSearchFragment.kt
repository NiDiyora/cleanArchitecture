package com.example.cleanarchitecture.presentation.meal_search

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentMealSearchBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MealSearchFragment : Fragment() {

    private var _binding: FragmentMealSearchBinding? = null
    val binding: FragmentMealSearchBinding?
        get() = _binding

    val mealSearchViewModel: MealSearchViewModel by viewModels()

    private val mealSearchAdapter = MealSearchAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentMealSearchBinding.inflate(inflater, container, false)


        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.searchMeal?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { mealSearchViewModel.searchMealList(it) }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        binding?.searchRecycler?.apply {
            adapter = mealSearchAdapter
        }

        mealSearchAdapter.itemClickListener {
            findNavController().navigate(
                MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment2(
                    it.mealId
                )
            )

        }

        lifecycle.coroutineScope.launchWhenCreated {
            mealSearchViewModel.mealSearchList.collect { it ->
                if (it.isLoading) {
                    binding?.progressMealSearch?.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding?.progressMealSearch?.visibility = View.GONE

                }
                it.data?.let {
                    binding?.progressMealSearch?.visibility = View.GONE
                    mealSearchAdapter.setContentList(it.toMutableList())
                    Log.d(TAG, "onViewCreated: $it")
                }
            }
        }
    }


}