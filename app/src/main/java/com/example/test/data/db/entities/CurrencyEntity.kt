package com.example.test.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.domain.model.Currency

/**
 * Class that describes a table in the database - Currency
 *
 * @property name name of the currency
 * @property value value of the currency
 * @property isSelected a state describing is this currency is added to favorites or not
 *
 * @author Evgen K.
 */
@Entity
class CurrencyEntity(
    @PrimaryKey
    val name: String,
    val value: String,
    val isSelected: Boolean
)

fun Currency.convertToEntity(): CurrencyEntity =
    CurrencyEntity(
        name = name,
        value = value,
        isSelected = isSelected
    )

fun CurrencyEntity.convertToCurrency(): Currency =
    Currency(
        name = name,
        value = value,
        isSelected = isSelected
    )
