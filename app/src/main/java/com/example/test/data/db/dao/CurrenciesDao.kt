package com.example.test.data.db.dao

import androidx.room.*
import com.example.test.data.db.entities.CurrencyEntity
import kotlinx.coroutines.flow.Flow

/**
 * Interface that describes interaction with the [CurrencyEntity] table of the database
 *
 * @author Evgen K.
 */
@Dao
interface CurrenciesDao {

    /**
     * Add [CurrencyEntity] object to database
     *
     * @param currency current currency item to be added to the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: CurrencyEntity)

    /**
     * Receive [CurrencyEntity] objects from database
     *
     * @return nullable Flow<MutableList<[CurrencyEntity]>>
     */
    @Query("SELECT * FROM currencyEntity")
    fun getCurrencies(): Flow<MutableList<CurrencyEntity>?>

    /**
     * Remove [CurrencyEntity] object from database
     *
     * @param currency current currency item to be removed from the database
     */
    @Delete
    suspend fun deleteCurrency(currency: CurrencyEntity)

}