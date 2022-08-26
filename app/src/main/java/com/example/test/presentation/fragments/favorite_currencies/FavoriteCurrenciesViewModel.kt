package com.example.test.presentation.fragments.favorite_currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.utils.Outcome
import com.example.test.domain.model.Currency
import com.example.test.domain.use_case.db_use_cases.DeleteCurrencyUseCase
import com.example.test.domain.use_case.db_use_cases.GetCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for working with [FavoriteCurrenciesFragment]
 *
 * @property getCurrencyUseCase receives main data with [Outcome] state from database
 * @property deleteCurrencyUseCase removes [Currency] item from database
 *
 * @author Evgen K.
 */
@HiltViewModel
class FavoriteCurrenciesViewModel @Inject constructor(
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val deleteCurrencyUseCase: DeleteCurrencyUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<FavoriteCurrenciesState>(FavoriteCurrenciesState.Empty)
    val state: StateFlow<FavoriteCurrenciesState> = _state

    init {
        getCurrencies()
    }

    private fun getCurrencies() {
        getCurrencyUseCase.invoke().onEach {
            when (it) {
                is Outcome.Loading -> {
                    _state.value = FavoriteCurrenciesState.Loading(it.isLoading)
                }
                is Outcome.Success -> {
                    _state.value = FavoriteCurrenciesState.Success(it.data)
                }
                is Outcome.Exception -> {
                    _state.value = FavoriteCurrenciesState.Exception(it.message)
                }
            }
        }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun removeCurrencyFromDb(currency: Currency) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCurrencyUseCase.invoke(currency)
        }
    }
}
