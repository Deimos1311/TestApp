package com.example.test.data.db.dao

import androidx.room.*
import com.example.test.data.db.entities.DataEntity

/**
 * Describes interaction with the [DataEntity] table of the database
 *
 * @author Evgen K.
 */
@Dao
interface DataDao {

    /**
     * Add [DataEntity] object to database
     *
     * @param data current data item to be added to the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: DataEntity)

    /**
     * Remove [DataEntity] object from database
     *
     * @param data current data item to be removed from the database
     */
    @Delete
    suspend fun deleteData(data: DataEntity)

    /**
     * Receive [DataEntity] objects from database
     *
     * @return nullable [DataEntity]
     */
    @Query("SELECT * FROM dataEntity")
    suspend fun getData(): DataEntity?

}