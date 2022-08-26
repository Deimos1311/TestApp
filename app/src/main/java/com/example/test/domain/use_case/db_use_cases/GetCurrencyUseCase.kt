package com.example.test.domain.use_case.db_use_cases

import com.example.test.data.db.entities.convertToCurrency
import com.example.test.utils.Outcome
import com.example.test.domain.model.Currency
import com.example.test.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    operator fun invoke(): Flow<Outcome<MutableList<Currency>>> = flow {
        databaseRepository.getCurrencies().collect {
            emit(Outcome.Loading(true))
            val currencies = mutableListOf<Currency>()
            it?.let { list ->
                list.forEach { item ->
                    currencies.add(item.convertToCurrency())
                }
            }
            emit(Outcome.Success(currencies))
        }
    }
}