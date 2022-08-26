package com.example.test.data.repository

import com.example.test.data.api.Api
import com.example.test.data.dto.convertToEntity
import com.example.test.domain.model.Currency
import com.example.test.domain.repository.ApiRepository
import com.example.test.domain.use_case.api_use_cases.TransformCurrenciesObjectUseCase
import com.example.test.domain.use_case.db_use_cases.InsertDataUseCase
import javax.inject.Inject

/**
 * Realization [ApiRepository]
 *
 * @property Api interface for interaction with REST API
 * @property InsertDataUseCase  use case for adding data to db
 * @property TransformCurrenciesObjectUseCase use case for transforming RatesDto object to MutableList<[Currency]>
 *
 * @author Evgen K.
 */
class ApiRepositoryImpl @Inject constructor(
    private val api: Api,
    private val insertDataUseCase: InsertDataUseCase,
    private val transformCurrenciesObjectUseCase: TransformCurrenciesObjectUseCase
) : ApiRepository {

    override suspend fun getAllCurrencies(currency: String): MutableList<Currency> {
        return transformCurrenciesObjectUseCase(
            api.getAllCurrencies(currency).also {
                insertDataUseCase(it.convertToEntity())
            }?.rates
        )
    }
}
