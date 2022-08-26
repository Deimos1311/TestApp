package com.example.test.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Class that describes a table in the database - Data
 *
 * @property base base currency relative to which the list of currencies is displayed
 * @property date current date
 * @property success is current request was successful or not
 * @property timestamp the exact time the request was made
 *
 * @author Evgen K.
 */
@Entity
data class DataEntity(
    @PrimaryKey(autoGenerate = false) val base: String,
    val date: String,
    val success: Boolean,
    val timestamp: Long
)
