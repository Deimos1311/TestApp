package com.example.test.domain.model

import com.example.test.utils.Const.UNAVAILABLE_BOOLEAN
import com.example.test.utils.Const.UNAVAILABLE_LONG
import com.example.test.utils.Const.UNAVAILABLE_STRING

/**
 * Domain model of the [Data]
 *
 * @property base base currency relative to which the list of currencies is displayed
 * @property date current date
 * @property rates typed elements ISO currency
 * @property success is current request was successful or not
 * @property timestamp the exact time the request was made
 *
 * @author Evgen K.
 */
data class Data(
    val base: String = UNAVAILABLE_STRING,
    val date: String = UNAVAILABLE_STRING,
    val rates: Rates = Rates(),
    val success: Boolean = UNAVAILABLE_BOOLEAN,
    val timestamp: Long = UNAVAILABLE_LONG
)