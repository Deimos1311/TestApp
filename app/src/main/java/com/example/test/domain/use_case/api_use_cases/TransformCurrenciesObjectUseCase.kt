package com.example.test.domain.use_case.api_use_cases

import com.example.test.data.dto.RatesDto
import com.example.test.domain.model.Currency
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

/**
 * Use Case that transform [RatesDto] to the MutableList<[Currency]> using reflection
 *
 * @author Evgen K.
 */
class TransformCurrenciesObjectUseCase @Inject constructor() {

    operator fun invoke(rates: RatesDto?): MutableList<Currency> {
        val listOfCurrencies = mutableListOf<Currency>()

        RatesDto::class.memberProperties.forEach { member ->
            val name = member.name
            val value = member.get(rates ?: RatesDto()) as String

            listOfCurrencies.add(Currency(name, value, false))
        }
        return listOfCurrencies
    }
}