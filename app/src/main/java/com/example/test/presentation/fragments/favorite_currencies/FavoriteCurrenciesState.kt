package com.example.test.presentation.fragments.favorite_currencies

import com.example.test.domain.model.Currency

/**
 * Sealed class that describes variants of [FavoriteCurrenciesFragment] state
 *
 * @author Evgen k.
 */
sealed class FavoriteCurrenciesState {
    /**
     * Loading state

     * @property isLoading contains loading state as true/false
     */
    data class Loading(val isLoading: Boolean) : FavoriteCurrenciesState()

    /**
     * Success state
     *
     * @property data contains received MutableList<[Currency]>
     */
    data class Success(val data: MutableList<Currency>) : FavoriteCurrenciesState()

    /**
     * Exception state
     *
     * @property message contains error message received from request
     */
    data class Exception(val message: String) : FavoriteCurrenciesState()

    /**
     * Empty state
     *
     * Initializing state
     */
    object Empty : FavoriteCurrenciesState()
}