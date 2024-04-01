package com.example.tipjar.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tipjar.model.entity.Computation
import com.example.tipjar.model.entity.Payment
import com.example.tipjar.model.repository.PaymentRepository
import com.example.tipjar.viewmodel.PaymentState.Companion.getDefault
import com.example.tipjar.viewmodel.PaymentState.Companion.showComputation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel
    @Inject
    constructor(private val repository: PaymentRepository) : ViewModel() {
        private val _amountState = MutableStateFlow("")
        private val _percentageState = MutableStateFlow("")
        private val _perPersonState = MutableStateFlow(1)
        private val _state = MutableStateFlow(PaymentState.getDefault())
        private val imageState = MutableStateFlow("")
        val amountState: StateFlow<String> get() = _amountState
        val percentageState: StateFlow<String> get() = _percentageState
        val perPersonState: StateFlow<Int> get() = _perPersonState

        val state: StateFlow<PaymentState> get() = _state

        fun addPerPerson(): (Int) -> Unit =
            { countPerPerson ->
                viewModelScope.launch {
                    _perPersonState.emit(repository.addPerPerson(countPerPerson))
                    showComputation()
                }
            }

        fun reducePerPerson(): (Int) -> Unit =
            { countPerPerson ->
                viewModelScope.launch {
                    _perPersonState.emit(repository.reducePerPerson(countPerPerson))
                    showComputation()
                }
            }

        fun changeAmount(): (String) -> Unit =
            { amount ->
                viewModelScope.launch {
                    _amountState.emit(repository.updateAmount(amount))
                    showComputation()
                }
            }

        fun changeTipPercentage(): (String) -> Unit =
            { percentage ->
                viewModelScope.launch {
                    _percentageState.emit(repository.updatePercentage(percentage))
                    showComputation()
                }
            }

        fun savePayment(): () -> Unit =
            {
                viewModelScope.launch {
                    if (_state.value is PaymentState.ComputePayment) {
                        val computePaymentState = (_state.value as PaymentState.ComputePayment)
                        val totalTip = computePaymentState.computation.totalTip
                        val payment = Payment(amount = _amountState.value, totalTip = totalTip)
                        onSaved(payment)
                    } else {
                        errorOnSaving()
                    }
                }
            }

        fun isPaymentNotEmpty(): Boolean {
            return _amountState.value.isNotBlank() &&
                _percentageState.value.isNotBlank() &&
                _perPersonState.value != 0
        }

        fun createImageIntentUri(): Uri {
            val receiptImage = repository.createReceiptImageUri()
            viewModelScope.launch { imageState.emit(receiptImage.timeStampId) }
            return receiptImage.uri
        }

        fun savePaymentWithImage(
            paymentAmount: String,
            paymentTotalTip: Float,
        ) {
            viewModelScope.launch {
                val payment =
                    Payment(
                        amount = paymentAmount,
                        totalTip = paymentTotalTip,
                        imageFile = imageState.value,
                    )
                save(payment)
                _state.emit(PaymentState.NavigateToHistory)
                delay(1000)
                _state.emit(PaymentState.ResetState)
            }
        }

        private suspend fun onSaved(payment: Payment) {
            if (isPaymentNotEmpty()) {
                save(payment)
            } else {
                errorOnSaving()
            }
        }

        private suspend fun save(payment: Payment) {
            repository.savePayment(payment)
            resetValues()
            delay(200)
            _state.emit(PaymentState.SavedPayment)
            delay(200)
            _state.emit(PaymentState.ResetState)
            delay(200)
        }

        private suspend fun errorOnSaving() {
            _state.emit(PaymentState.Error)
            delay(2000)
            _state.emit(PaymentState.ResetState)
        }

        private suspend fun showComputation() {
            val computation =
                repository.computePayment(
                    Payment(
                        amount = _amountState.value,
                        percentage = _percentageState.value,
                        countPerPerson = perPersonState.value,
                    ),
                )
            _state.emit(PaymentState.showComputation(computation))
        }

        private suspend fun resetValues() {
            _amountState.emit("")
            _percentageState.emit("")
            _perPersonState.emit(1)
            _state.emit(PaymentState.getDefault())
        }
    }

sealed class PaymentState {
    data class Default(val payment: Payment) : PaymentState()

    data class ComputePayment(val computation: Computation) : PaymentState()

    data object SavedPayment : PaymentState()

    data object Error : PaymentState()

    data object ResetState : PaymentState()

    data object NavigateToHistory : PaymentState()

    companion object {
        fun Companion.getDefault(): PaymentState {
            return Default(Payment())
        }

        fun Companion.showComputation(computation: Computation): PaymentState {
            return ComputePayment(computation)
        }
    }
}
