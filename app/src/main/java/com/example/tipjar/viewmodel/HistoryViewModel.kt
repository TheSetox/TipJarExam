package com.example.tipjar.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tipjar.model.entity.PaymentHistory
import com.example.tipjar.model.entity.PaymentHistory.Companion.previewList
import com.example.tipjar.model.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel
    @Inject
    constructor(private val repository: PaymentRepository) : ViewModel() {
        private val _state: MutableStateFlow<PaymentHistoryState> =
            MutableStateFlow(PaymentHistoryState.Empty)

        val state: StateFlow<PaymentHistoryState> get() = _state

        fun getListOfPayments() =
            viewModelScopeLauncher {
                val listOfPayments = repository.getListOfPayments()
                _state.emit(PaymentHistoryState.ShowList(listOfPayments))
            }

        fun deletePayment(): (String) -> Unit =
            {
                viewModelScopeLauncher {
                    Log.d("DELETE", "deletePayment: $it")
                    repository.deletePayment(it)
                }
            }

        private fun viewModelScopeLauncher(function: suspend () -> Unit) =
            viewModelScope.launch {
                function()
            }
    }

sealed class PaymentHistoryState {
    data object Empty : PaymentHistoryState()

    data class ShowList(val list: Flow<List<PaymentHistory>>) : PaymentHistoryState()

    companion object {
        fun Companion.showPreviewList(): ShowList {
            return ShowList(PaymentHistory.previewList())
        }
    }
}
