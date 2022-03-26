package com.example.cleanarchitecture.presentation.meal_details

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cleanarchitecture.databinding.FragmentMealDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MealDetailsFragment : Fragment() {


    private var _binding: FragmentMealDetailsBinding? = null
    val binding: FragmentMealDetailsBinding?
        get() = _binding

    var meal_id: String? = null
    val mealDetailsViewModel: MealDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        if (arguments != null) {
            meal_id = requireArguments().getString("meal_id")
            // The getPrivacyPolicyLink() method will be created automatically.
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)


        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        meal_id?.let { mealDetailsViewModel.getMealDetails(it) }
        lifecycle.coroutineScope.launchWhenCreated {
            mealDetailsViewModel.mealDetails.collect { it ->
                if (it.isLoading) {
                    binding?.progressMealDetails?.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding?.progressMealDetails?.visibility = View.GONE
                }
                it.data.let {
                    binding?.progressMealDetails?.visibility = View.GONE
                    binding?.mealdetails = it

                }

            }
        }
    }


}