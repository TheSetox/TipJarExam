package com.example.tipjar.view.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tipjar.util.floatToCurrency
import com.example.tipjar.view.component.payment.ComputationSummaryRow
import com.example.tipjar.view.component.payment.EntryBox
import com.example.tipjar.view.component.payment.PaymentActionRow
import com.example.tipjar.view.component.payment.PeopleCounterRow
import com.example.tipjar.view.component.payment.Position
import com.example.tipjar.view.component.topbar.PaymentTopBar
import com.example.tipjar.viewmodel.PaymentState
import com.example.tipjar.viewmodel.PaymentState.Companion.getDefault
import com.example.tipjar.viewmodel.PaymentViewModel
import com.thesetox.prepare.Prepare
import com.thesetox.prepare.PreparePreview

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PreparePreview {
        PaymentScreen()
    }
}

@Composable
fun PaymentScreen(
    myViewModel: (PaymentViewModel) -> Unit = {},
    onLaunchCamera: (String, Float) -> Unit = { _, _ -> },
    navigate: () -> Unit = {},
) {
    var amountState: State<String> = rememberSaveable { mutableStateOf("") }
    var percentageState: State<String> = rememberSaveable { mutableStateOf("") }
    var perPersonState: State<Int> = rememberSaveable { mutableIntStateOf(1) }
    var computationState: State<PaymentState> =
        remember {
            mutableStateOf(PaymentState.getDefault())
        }
    var onAddPerPerson: Int.() -> Unit = {}
    var onReducePerPerson: Int.() -> Unit = {}
    var onAmountChanged: String.() -> Unit = {}
    var onTipPercentageChanged: String.() -> Unit = {}
    var onSavePayment: () -> Unit = {}
    var isPaymentPopulated: () -> Boolean = { false }

    Prepare(
        preview = {},
        data = {
            val paymentViewModel: PaymentViewModel = hiltViewModel()
            amountState = paymentViewModel.amountState.collectAsState()
            percentageState = paymentViewModel.percentageState.collectAsState()
            perPersonState = paymentViewModel.perPersonState.collectAsState()
            computationState = paymentViewModel.state.collectAsState()
            onAddPerPerson = paymentViewModel.addPerPerson()
            onReducePerPerson = paymentViewModel.reducePerPerson()
            onAmountChanged = paymentViewModel.changeAmount()
            onTipPercentageChanged = paymentViewModel.changeTipPercentage()
            onSavePayment = paymentViewModel.savePayment()
            isPaymentPopulated = { paymentViewModel.isPaymentNotEmpty() }
            myViewModel(paymentViewModel)
        },
        screen = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { PaymentTopBar(navigate) },
                content = {
                    it.PaymentContent(
                        navigate,
                        amountState,
                        percentageState,
                        perPersonState,
                        computationState,
                        onAddPerPerson,
                        onReducePerPerson,
                        onAmountChanged,
                        onTipPercentageChanged,
                        onSavePayment,
                        onLaunchCamera,
                        isPaymentPopulated,
                    )
                },
            )
        },
    )
}

@Composable
private fun PaddingValues.PaymentContent(
    onNavigate: () -> Unit,
    amountState: State<String>,
    percentageState: State<String>,
    perPersonState: State<Int>,
    computationState: State<PaymentState>,
    onAddPerPerson: Int.() -> Unit,
    onReducePerPerson: Int.() -> Unit,
    onAmountChanged: String.() -> Unit,
    onTipPercentageChanged: String.() -> Unit,
    onSavePayment: () -> Unit,
    onLaunchCamera: (String, Float) -> Unit,
    isPaymentPopulated: () -> Boolean,
) {
    val spacerModifier = Modifier.padding(16.dp)
    val totalTip = rememberSaveable { mutableFloatStateOf(0F) }
    val tipPerPerson = rememberSaveable { mutableFloatStateOf(0F) }

    PrepareState(onNavigate, computationState, totalTip, tipPerPerson)

    Column(
        modifier =
            Modifier
                .padding(top = calculateTopPadding())
                .padding(horizontal = 20.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding(),
    ) {
        EntryBox(
            mainText = amountState.value,
            label = "Enter amount",
            hintText = "100.00",
            helperText = "$",
            helperPosition = Position.LEFT,
            onValueChanged = onAmountChanged,
        )
        Spacer(spacerModifier)
        PeopleCounterRow(
            counter = perPersonState.value,
            onAddPeople = onAddPerPerson,
            onReducePeople = onReducePerPerson,
        )
        Spacer(spacerModifier)
        EntryBox(
            mainText = percentageState.value,
            label = "% TIP",
            helperText = "%",
            hintText = "10.00",
            helperPosition = Position.RIGHT,
            onValueChanged = onTipPercentageChanged,
        )
        Spacer(spacerModifier)
        ComputationSummaryRow(
            totalTip = totalTip.floatValue.floatToCurrency(),
            totalTipPerPerson = tipPerPerson.floatValue.floatToCurrency(),
        )
        Spacer(Modifier.weight(1f))
        PaymentActionRow(
            onSave = { isChecked ->
                if (isChecked && isPaymentPopulated()) {
                    onLaunchCamera(amountState.value, totalTip.floatValue)
                } else {
                    onSavePayment()
                }
            },
        )
    }
}

@Composable
private fun PrepareState(
    onNavigate: () -> Unit,
    computationState: State<PaymentState>,
    totalTip: MutableFloatState,
    tipPerPerson: MutableFloatState,
) {
    when (val state = computationState.value) {
        is PaymentState.ComputePayment -> {
            totalTip.floatValue = state.computation.totalTip
            tipPerPerson.floatValue = state.computation.tipPerPerson
        }

        is PaymentState.Default -> {
            totalTip.floatValue = state.payment.totalTip
            tipPerPerson.floatValue = state.payment.totalTipPerPerson
        }

        PaymentState.Error ->
            Toast.makeText(LocalContext.current, "Error: No data entered", Toast.LENGTH_SHORT)
                .show()

        PaymentState.SavedPayment ->
            Toast.makeText(LocalContext.current, "Saved Payment", Toast.LENGTH_SHORT).show()

        PaymentState.NavigateToHistory -> onNavigate()

        else -> Log.d("PaymentScreen", "PaymentContent: end state")
    }
}
