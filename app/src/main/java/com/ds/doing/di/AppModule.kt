package com.ds.doing.di

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.ds.doing.DoingDatabase
import com.ds.doing.data.repos.TasKRepositoryImpl
import com.ds.doing.data.repos.TaskRepositoryMemoryImp
import com.ds.doing.data.repos.doingAdapter
import com.ds.doing.domain.repos.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import data.TaskEntity
import data.TaskQueries
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InMemoryRepository

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DBRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @InMemoryRepository
    @Binds
    @Singleton
    abstract fun bindsInMemoryTaskRepository(
        tasksRepository: TaskRepositoryMemoryImp
    ): TaskRepository

    @DBRepository
    @Binds
    @Singleton
    abstract fun bindsDBRepository(
        tasksRepository: TasKRepositoryImpl
    ): TaskRepository
}

@Module
@InstallIn(SingletonComponent::class)
object MyModule {
    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): TaskQueries {
        val driver = AndroidSqliteDriver(
            DoingDatabase.Schema,
            context,
            "doing"
        )
        return DoingDatabase(
            driver = driver,
            TaskEntityAdapter =
            TaskEntity.Adapter(
                statusAdapter = doingAdapter
            )
        ).taskQueries
    }
}
