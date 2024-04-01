package com.example.tipjar.viewmodel

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
        val amountState: StateFlow<String> get() = _amountState
        val percentageState: StateFlow<String> get() = _percentageState
        val perPersonState: StateFlow<Int> get() = _perPersonState

        val state: StateFlow<PaymentState> get() = _state

        fun addPerPerson(): Int.() -> Unit =
            {
                val countPerPerson = this
                viewModelScope.launch {
                    _perPersonState.emit(repository.addPerPerson(countPerPerson))
                    showComputation()
                }
            }

        fun reducePerPerson(): Int.() -> Unit =
            {
                val countPerPerson = this
                viewModelScope.launch {
                    _perPersonState.emit(repository.reducePerPerson(countPerPerson))
                    showComputation()
                }
            }

        fun changeAmount(): String.() -> Unit =
            {
                val amount = this
                viewModelScope.launch {
                    _amountState.emit(repository.updateAmount(amount))
                    showComputation()
                }
            }

        fun changeTipPercentage(): String.() -> Unit =
            {
                val percentage = this
                viewModelScope.launch {
                    _percentageState.emit(repository.updatePercentage(percentage))
                    showComputation()
                }
            }

        fun savePayment(): () -> Unit =
            {
                viewModelScope.launch {
                    if (_amountState.value.isNotBlank() &&
                        _percentageState.value.isNotBlank() &&
                        _perPersonState.value != 0
                    ) {
                        val totalTip =
                            (_state.value as PaymentState.ComputePayment).computation.totalTip

                        val payment =
                            Payment(
                                amount = _amountState.value,
                                totalTip = totalTip,
                            )
                        repository.savePayment(payment)
                        resetValues()
                        _state.emit(PaymentState.SavedPayment)
                        delay(2000)
                        _state.emit(PaymentState.ResetState)
                    } else {
                        _state.emit(PaymentState.Error)
                        delay(2000)
                        _state.emit(PaymentState.ResetState)
                    }
                }
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

    companion object {
        fun Companion.getDefault(): PaymentState {
            return Default(Payment())
        }

        fun Companion.showComputation(computation: Computation): PaymentState {
            return ComputePayment(computation)
        }
    }
}
