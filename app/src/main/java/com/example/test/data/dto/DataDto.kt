package com.example.test.data.dto

import com.example.test.data.db.entities.DataEntity
import com.example.test.domain.model.Data
import com.example.test.utils.Const.UNAVAILABLE_BOOLEAN
import com.example.test.utils.Const.UNAVAILABLE_LONG
import com.example.test.utils.Const.UNAVAILABLE_STRING
import com.google.gson.annotations.SerializedName

/**
 * [DataDto] object to [DataEntity]
 * [DataDto] object to [Data]
 *
 * @property @ignore nullable [base] base currency relative to which the list of currencies is displayed
 * @property @ignore nullable [date] current date
 * @property @ignore nullable [rates] typed elements ISO currency
 * @property @ignore nullable [success] is current request was successful or not
 * @property @ignore nullable [timestamp] the exact time the request was made
 *
 * @author Evgen K.
 */
data class DataDto(
    @SerializedName("base")
    var base: String? = null,

    @SerializedName("date")
    var date: String? = null,

    @SerializedName("rates")
    var rates: RatesDto? = RatesDto(),

    @SerializedName("success")
    var success: Boolean? = null,

    @SerializedName("timestamp")
    var timestamp: Long? = null
)

fun DataDto?.convertToEntity(): DataEntity =
    this?.let {
        DataEntity(
            base = it.base ?: UNAVAILABLE_STRING,
            date = it.date ?: UNAVAILABLE_STRING,
            success = it.success ?: UNAVAILABLE_BOOLEAN,
            timestamp = it.timestamp ?: UNAVAILABLE_LONG
        )
    } ?: throw Throwable("DataDto is empty")

fun DataDto?.convertToModel(): Data =
    this?.let {
        val rates = it.rates.convertToModel()

        Data(
            base = it.base ?: UNAVAILABLE_STRING,
            date = it.date ?: UNAVAILABLE_STRING,
            rates = rates,
            success = it.success ?: UNAVAILABLE_BOOLEAN,
            timestamp = it.timestamp ?: UNAVAILABLE_LONG
        )
    } ?: throw Throwable("DataDto is empty")