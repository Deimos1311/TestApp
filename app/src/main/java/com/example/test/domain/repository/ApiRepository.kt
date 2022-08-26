package com.example.test.domain.repository

import com.example.test.domain.model.Currency

/**
 * Interface for getting data from API
 *
 * @author Evgen K.
 */
interface ApiRepository {

    /**
     * Get list of currencies by entered value
     *
     * @param currency current value of the currency for the query in the string
     *
     * @return MutableList<[Currency]>
     */
    suspend fun getAllCurrencies(currency: String): MutableList<Currency>

}