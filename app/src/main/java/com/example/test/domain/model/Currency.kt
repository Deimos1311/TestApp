package com.example.test.domain.model

/**
 * Domain Model of the [Currency]
 *
 * @property name name of the currency
 * @property value value of the currency
 * @property isSelected a state describing is this currency is added to favorites or not
 *
 * @author Evgen K.
 */
data class Currency(
    val name: String,
    val value: String,
    var isSelected: Boolean = false
)