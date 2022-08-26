package com.example.test.domain.use_case.db_use_cases

import com.example.test.data.db.entities.convertToEntity
import com.example.test.domain.model.Currency
import com.example.test.domain.repository.DatabaseRepository
import javax.inject.Inject

class InsertCurrencyUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(currency: Currency) =
        databaseRepository.insertCurrency(currency.convertToEntity())
}