package com.example.tipjar.di

import com.example.tipjar.model.source.LocalSource
import com.example.tipjar.model.source.impl.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindLocalSource(localDataSource: LocalDataSource): LocalSource
}
