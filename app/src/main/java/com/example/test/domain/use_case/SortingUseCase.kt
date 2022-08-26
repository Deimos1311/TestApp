package com.example.test.domain.use_case

import com.example.test.domain.model.Currency

class SortingUseCase {

    operator fun invoke(
        currencies: MutableList<Currency>,
        sortingType: String
    ): MutableList<Currency> {
        when (sortingType) {
            "A-Z" -> {
                currencies.sortBy { it.name }
            }
            "Z-A" -> {
                currencies.sortByDescending { it.name }
            }
            "Min -> Max" -> {
                currencies.sortBy { it.value.toDouble() }
            }
            "Max -> Min" -> {
                currencies.sortByDescending { it.value.toDouble() }
            }
        }
        return currencies
    }
}