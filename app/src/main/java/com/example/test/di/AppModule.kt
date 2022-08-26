package com.example.test.di

import android.app.Application
import androidx.room.Room
import com.example.test.BuildConfig
import com.example.test.data.api.Api
import com.example.test.data.db.Database
import com.example.test.data.db.dao.CurrenciesDao
import com.example.test.data.db.dao.DataDao
import com.example.test.data.db.dao.RatesDao
import com.example.test.data.repository.ApiRepositoryImpl
import com.example.test.data.repository.DatabaseRepositoryImpl
import com.example.test.domain.repository.ApiRepository
import com.example.test.domain.repository.DatabaseRepository
import com.example.test.domain.use_case.api_use_cases.TransformCurrenciesObjectUseCase
import com.example.test.domain.use_case.db_use_cases.InsertDataUseCase
import com.example.test.utils.Const
import com.example.test.utils.Const.TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Module that describes creating dependencies for an app
 *
 * @author Evgen K.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient): Api {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideDatabase(application: Application): Database =
        Room.databaseBuilder(application, Database::class.java, Const.databaseName)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDataDao(database: Database): DataDao = database.dataDao

    @Provides
    @Singleton
    fun provideRatesDao(database: Database): RatesDao = database.ratesDao

    @Provides
    @Singleton
    fun provideCurrenciesDao(database: Database): CurrenciesDao = database.currenciesDao

    @Provides
    @Singleton
    fun provideDatabaseRepository(
        dataDao: DataDao,
        currenciesDao: CurrenciesDao
    ): DatabaseRepository = DatabaseRepositoryImpl(dataDao, currenciesDao)

    @Provides
    @Singleton
    fun provideCurrenciesRepository(
        api: Api,
        insertDataUseCase: InsertDataUseCase,
        transformCurrenciesObjectUseCase: TransformCurrenciesObjectUseCase
    ): ApiRepository =
        ApiRepositoryImpl(
            api,
            insertDataUseCase,
            transformCurrenciesObjectUseCase
        )
}
