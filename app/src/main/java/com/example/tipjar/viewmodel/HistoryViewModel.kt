package com.example.tipjar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tipjar.model.entity.PaymentHistory
import com.example.tipjar.model.entity.PaymentHistory.Companion.previewList
import com.example.tipjar.model.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel
    @Inject
    constructor(private val repository: PaymentRepository) : ViewModel() {
        private val _state: MutableStateFlow<PaymentState> = MutableStateFlow(PaymentState.Empty)

        val state: StateFlow<PaymentState> get() = _state

        fun getListOfPayments() =
            viewModelScope.launch {
                val listOfPayments = repository.getListOfPayments()
                _state.emit(PaymentState.ShowList(listOfPayments))
            }
    }

sealed class PaymentState {
    data object Empty : PaymentState()

    data class ShowList(val list: List<PaymentHistory>) : PaymentState()

    companion object {
        fun Companion.showPreviewList(): ShowList {
            return ShowList(PaymentHistory.previewList())
        }
    }
}
