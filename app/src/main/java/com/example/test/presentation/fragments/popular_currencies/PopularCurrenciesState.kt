package com.example.test.presentation.fragments.popular_currencies

import com.example.test.domain.model.Currency

/**
 * Sealed class that describes variants of [PopularCurrenciesFragment] state
 *
 * @author Evgen k.
 */
sealed class PopularCurrenciesState {

    /**
     * Loading state

     * @property isLoading contains loading state as true/false
     */
    data class Loading(val isLoading: Boolean) : PopularCurrenciesState()

    /**
     * Success state
     *
     * @property data contains received MutableList<[Currency]>
     */
    data class Success(val data: MutableList<Currency>) : PopularCurrenciesState()

    /**
     * Exception state
     *
     * @property message contains error message received from request
     */
    data class Exception(val message: String) : PopularCurrenciesState()

    /**
     * Empty state
     *
     * Initializing state
     */
    object Empty : PopularCurrenciesState()
}