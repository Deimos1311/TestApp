package com.example.test.domain.use_case.interactors

import com.example.test.utils.Outcome
import com.example.test.domain.model.Currency
import com.example.test.domain.use_case.SortingUseCase
import com.example.test.domain.use_case.api_use_cases.GetAllCurrenciesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Interactor that describes how we receive data from API,
 * adding them to the database, sorting by sort types and converting to the [Currency] model
 *
 * @property getAllCurrenciesUseCase use case that receive all data from Api
 * @property sortingUseCase use case that sorting received data by sort types
 *
 * @throws HttpException
 * @throws IOException
 *
 * @author Evgen K.
 */
class GetAllCurrenciesInteractor @Inject constructor(
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase,
    private val sortingUseCase: SortingUseCase
) {

    operator fun invoke(
        currency: String,
        sortingType: String
    ): Flow<Outcome<MutableList<Currency>>> = flow {
        emit(Outcome.Loading(true))
        try {
            val currencies = getAllCurrenciesUseCase.invoke(currency)
            sortingUseCase.invoke(currencies, sortingType)
            emit(Outcome.Success(currencies))
        } catch (ex: HttpException) {
            emit(
                Outcome.Exception(ex.localizedMessage ?: "Unexpected HTTPException")
            )
        } catch (e: IOException) {
            emit(
                Outcome.Exception(e.localizedMessage ?: "Unexpected IOException")
            )
        }
    }
}