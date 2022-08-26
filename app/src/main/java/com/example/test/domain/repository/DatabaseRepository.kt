package com.example.test.domain.repository

import com.example.test.data.db.entities.CurrencyEntity
import com.example.test.data.db.entities.DataEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for working with database requests
 *
 * @author Evgen Kolesnik
 */
interface DatabaseRepository {

    /**
     * Add [DataEntity] object to database
     *
     * @param data object to be added to the database
     */
    suspend fun insertData(data: DataEntity)

    /**
     * Remove [DataEntity] object from database
     *
     * @param data object to be removed from the database
     */
    suspend fun deleteData(data: DataEntity)

    /**
     * Receive [DataEntity] object from database
     *
     * @return nullable [DataEntity]
     */
    suspend fun getData(): DataEntity?

    /**
     * Add [CurrencyEntity] object to database
     *
     * @param currency object to be added to the database
     */
    suspend fun insertCurrency(currency: CurrencyEntity)

    /**
     * Receive [CurrencyEntity] objects from database
     *
     * @return nullable Flow<MutableList<[CurrencyEntity]>>>
     */
    suspend fun getCurrencies(): Flow<MutableList<CurrencyEntity>?>

    /**
     * Remove [CurrencyEntity] from database
     *
     * @param currency object to be removed from the database
     */
    suspend fun deleteCurrency(currency: CurrencyEntity)
}
