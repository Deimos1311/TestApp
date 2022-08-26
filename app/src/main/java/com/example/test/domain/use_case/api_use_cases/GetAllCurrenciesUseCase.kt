package com.example.test.domain.use_case.api_use_cases

import com.example.test.domain.model.Currency
import com.example.test.domain.repository.ApiRepository
import javax.inject.Inject

class GetAllCurrenciesUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke(currency: String): MutableList<Currency> =
        apiRepository.getAllCurrencies(currency)
}