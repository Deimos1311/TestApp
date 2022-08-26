package com.example.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test.data.db.dao.CurrenciesDao
import com.example.test.data.db.dao.DataDao
import com.example.test.data.db.dao.RatesDao
import com.example.test.data.db.entities.CurrencyEntity
import com.example.test.data.db.entities.DataEntity
import com.example.test.data.db.entities.RatesEntity

/**
 * Class that create database current tables : [DataEntity], [RatesEntity], [CurrencyEntity]
 *
 * @author Evgen K.
 */
@Database(
    entities = [DataEntity::class, RatesEntity::class, CurrencyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract val dataDao: DataDao

    abstract val ratesDao: RatesDao

    abstract val currenciesDao: CurrenciesDao

}