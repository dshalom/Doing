package com.ds.doing.di

import com.ds.doing.data.repos.TaskRepositoryMemoryImp
import com.ds.doing.domain.repos.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindsTaskRepository(
        tasksRepository: TaskRepositoryMemoryImp
    ): TaskRepository
}
