package com.example.test.utils

/**
 * Sealed class that describes variants of answers from the Api
 *
 * @author Evgen K.
 */
sealed class Outcome<T> {
    /**
     * Loading state
     *
     * @property isLoading contains loading state as true/false
     */
    data class Loading<T>(val isLoading: Boolean) : Outcome<T>()

    /**
     * Success state
     *
     * @property data contains received data from Api
     */
    data class Success<T>(val data: T) : Outcome<T>()

    /**
     * Exception state
     *
     * @property message contains error message from Api request
     */
    data class Exception<T>(val message: String) : Outcome<T>()
}