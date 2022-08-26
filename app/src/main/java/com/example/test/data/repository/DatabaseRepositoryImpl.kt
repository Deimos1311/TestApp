package com.example.test.data.repository

import com.example.test.data.db.dao.CurrenciesDao
import com.example.test.data.db.dao.DataDao
import com.example.test.data.db.entities.CurrencyEntity
import com.example.test.data.db.entities.DataEntity
import com.example.test.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Realization [DatabaseRepository]
 *
 * @property [DataDao] DAO for interaction with database table [DataEntity]
 * @property [CurrenciesDao] DAO for interaction with database table [CurrencyEntity]
 *
 * @author Evgen K.
 */
class DatabaseRepositoryImpl @Inject constructor(
    private val dataDao: DataDao,
    private val currenciesDao: CurrenciesDao
) : DatabaseRepository {

    override suspend fun insertData(data: DataEntity) = dataDao.insertData(data)

    override suspend fun deleteData(data: DataEntity) = dataDao.deleteData(data)

    override suspend fun getData(): DataEntity? = dataDao.getData()

    override suspend fun insertCurrency(currency: CurrencyEntity) =
        currenciesDao.insertCurrency(currency)

    override suspend fun getCurrencies(): Flow<MutableList<CurrencyEntity>?> =
        currenciesDao.getCurrencies()

    override suspend fun deleteCurrency(currency: CurrencyEntity) =
        currenciesDao.deleteCurrency(currency)

}