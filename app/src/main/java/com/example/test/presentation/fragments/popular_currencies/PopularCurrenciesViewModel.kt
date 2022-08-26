package com.example.test.presentation.fragments.popular_currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.model.Currency
import com.example.test.domain.use_case.db_use_cases.DeleteCurrencyUseCase
import com.example.test.domain.use_case.db_use_cases.GetCurrencyUseCase
import com.example.test.domain.use_case.db_use_cases.InsertCurrencyUseCase
import com.example.test.domain.use_case.interactors.GetAllCurrenciesInteractor
import com.example.test.utils.Const.DEFAULT_CURRENCY
import com.example.test.utils.Const.DEFAULT_SORTING_TYPE
import com.example.test.utils.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for working with [PopularCurrenciesFragment]
 *
 * @property insertCurrencyUseCase adds [Currency] item to the database
 * @property getCurrencyUseCase receives main data with [Outcome] state from database
 * @property deleteCurrencyUseCase removes [Currency] item from database
 * @property getAllCurrenciesInteractor contains all filtered ready data with [Outcome] state, receives from Api
 *
 * @author Evgen K.
 */
@HiltViewModel
class PopularCurrenciesViewModel @Inject constructor(
    private val insertCurrencyUseCase: InsertCurrencyUseCase,
    private val deleteCurrencyUseCase: DeleteCurrencyUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val getAllCurrenciesInteractor: GetAllCurrenciesInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<PopularCurrenciesState>(PopularCurrenciesState.Empty)
    val state: StateFlow<PopularCurrenciesState> = _state

    init {
        getAllCurrencies(DEFAULT_CURRENCY, DEFAULT_SORTING_TYPE)
    }

    fun getAllCurrencies(currency: String, sortingType: String) {
        getAllCurrenciesInteractor.invoke(currency, sortingType).onEach {
            when (it) {
                is Outcome.Loading -> {
                    _state.value = PopularCurrenciesState.Loading(it.isLoading)
                }
                is Outcome.Success -> {
                    _state.value = PopularCurrenciesState.Success(compareDataFromDb(it.data))
                }
                is Outcome.Exception -> {
                    _state.value = PopularCurrenciesState.Exception(it.message)
                }
            }
        }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private fun compareDataFromDb(data: MutableList<Currency>): MutableList<Currency> {
        getCurrencyUseCase.invoke().onEach {
            when (it) {
                is Outcome.Success -> {
                    it.data.forEach { currency ->
                        data.forEach { item ->
                            if (currency.name.contains(item.name)) {
                                item.isSelected = true
                            }
                        }
                    }
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
        return data
    }

    fun addCurrencyToDb(currency: Currency) {
        viewModelScope.launch(Dispatchers.IO) {
            insertCurrencyUseCase.invoke(currency)
        }
    }

    fun removeCurrencyFromDb(currency: Currency) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCurrencyUseCase.invoke(currency)
        }
    }
}
