package com.example.tipjar.di

import com.example.tipjar.model.repository.PaymentDataRepository
import com.example.tipjar.model.repository.PaymentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPaymentRepository(repository: PaymentDataRepository): PaymentRepository
}
