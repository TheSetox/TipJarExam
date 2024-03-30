package com.example.tipjar.di

import com.example.tipjar.model.source.ImageSource
import com.example.tipjar.model.source.LocalSource
import com.example.tipjar.model.source.PaymentSource
import com.example.tipjar.model.source.TimeStampSource
import com.example.tipjar.model.source.impl.ImageDataSource
import com.example.tipjar.model.source.impl.LocalDataSource
import com.example.tipjar.model.source.impl.PaymentDataSource
import com.example.tipjar.model.source.impl.TimeStampDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindLocalSource(localDataSource: LocalDataSource): LocalSource

    @Binds
    abstract fun bindTimeStampSource(timeStampDataSource: TimeStampDataSource): TimeStampSource

    @Binds
    abstract fun bindImageSource(imageDataSource: ImageDataSource): ImageSource

    @Binds
    abstract fun bindPaymentSource(paymentDataSource: PaymentDataSource): PaymentSource
}
