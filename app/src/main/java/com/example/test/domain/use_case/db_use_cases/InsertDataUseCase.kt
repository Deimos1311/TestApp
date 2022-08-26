package com.example.test.domain.use_case.db_use_cases

import com.example.test.data.db.entities.DataEntity
import com.example.test.domain.repository.DatabaseRepository
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(data: DataEntity) = databaseRepository.insertData(data)

}