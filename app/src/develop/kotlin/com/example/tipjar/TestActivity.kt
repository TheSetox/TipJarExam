package com.example.tipjar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.tipjar.model.database.TipDatabase
import com.example.tipjar.model.source.impl.LocalDataSource
import com.example.tipjar.view.App
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The purpose of this app is to test hilt dependency if it is working and acts as a
 * development environment.
 */
@AndroidEntryPoint
class TestActivity : ComponentActivity() {
    @Inject
    lateinit var localDataSource: LocalDataSource

    @Inject
    lateinit var database: TipDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            Log.d(
                "TestActivity",
                "onCreate getListOfPayments: ${localDataSource.getListOfPayments()}",
            )
            Log.d(
                "TestActivity",
                "onCreate getListOfPaymentHistory: ${
                    database.paymentHistoryDao().getListOfPaymentHistory()
                }",
            )
        }
        setContent { App() }
    }
}
