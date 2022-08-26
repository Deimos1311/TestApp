package com.example.test.di

import com.example.test.domain.repository.ApiRepository
import com.example.test.domain.repository.DatabaseRepository
import com.example.test.domain.use_case.SortingUseCase
import com.example.test.domain.use_case.api_use_cases.GetAllCurrenciesUseCase
import com.example.test.domain.use_case.api_use_cases.TransformCurrenciesObjectUseCase
import com.example.test.domain.use_case.db_use_cases.*
import com.example.test.domain.use_case.interactors.GetAllCurrenciesInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Module that creates use cases for an app
 *
 * @author Evgen K.
 */
@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun transformCurrenciesObjectUseCase(): TransformCurrenciesObjectUseCase =
        TransformCurrenciesObjectUseCase()

    @Provides
    fun provideGetAllCurrenciesUseCase(apiRepository: ApiRepository): GetAllCurrenciesUseCase =
        GetAllCurrenciesUseCase(apiRepository)


    @Provides
    fun provideInsertDataUseCase(databaseRepository: DatabaseRepository): InsertDataUseCase =
        InsertDataUseCase(databaseRepository)

    @Provides
    fun provideDeleteDataUseCase(databaseRepository: DatabaseRepository): DeleteDataUseCase =
        DeleteDataUseCase(databaseRepository)

    @Provides
    fun provideGetDataUseCase(databaseRepository: DatabaseRepository): GetDataUseCase =
        GetDataUseCase(databaseRepository)


    @Provides
    fun provideInsertCurrencyUseCase(databaseRepository: DatabaseRepository): InsertCurrencyUseCase =
        InsertCurrencyUseCase(databaseRepository)

    @Provides
    fun provideGetCurrencyUseCase(databaseRepository: DatabaseRepository): GetCurrencyUseCase =
        GetCurrencyUseCase(databaseRepository)

    @Provides
    fun provideDeleteCurrencyUseCase(databaseRepository: DatabaseRepository): DeleteCurrencyUseCase =
        DeleteCurrencyUseCase(databaseRepository)

    @Provides
    fun provideSortingUseCase(): SortingUseCase = SortingUseCase()

    @Provides
    fun provideGetAllCurrenciesInteractor(
        getAllCurrenciesUseCase: GetAllCurrenciesUseCase,
        sortingUseCase: SortingUseCase
    ): GetAllCurrenciesInteractor =
        GetAllCurrenciesInteractor(getAllCurrenciesUseCase, sortingUseCase)
}