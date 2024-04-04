package com.example.tipjar.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tipjar.R
import com.example.tipjar.model.entity.PaymentHistory
import com.example.tipjar.model.entity.PaymentHistory.Companion.defaultData
import com.example.tipjar.util.floatToCurrency
import com.example.tipjar.view.LocalPreviewMode
import com.example.tipjar.view.component.history.ViewPaymentHistoryDialog
import com.example.tipjar.view.component.topbar.HistoryTopBar
import com.example.tipjar.view.labelTextStyle
import com.example.tipjar.view.normalTextStyle
import com.example.tipjar.view.subtitleTextStyle
import com.example.tipjar.viewmodel.HistoryViewModel
import com.example.tipjar.viewmodel.PaymentHistoryState
import com.example.tipjar.viewmodel.PaymentHistoryState.Companion.showPreviewList

@Preview
@Composable
fun HistoryScreenPreview() {
    CompositionLocalProvider(LocalPreviewMode provides true) {
        HistoryScreen()
    }
}

@Composable
fun PrepareScreen(
    onPreview: () -> Unit,
    onViewModel: @Composable () -> Unit,
    onDialog: @Composable () -> Unit,
    loadScreen: @Composable () -> Unit,
) {
    if (LocalPreviewMode.current) {
        onPreview()
    } else {
        onViewModel()
    }

    loadScreen()
    onDialog()
}

@Composable
fun HistoryScreen(navigate: () -> Unit = {}) {
    var paymentHistoryState: State<PaymentHistoryState> =
        remember {
            mutableStateOf(PaymentHistoryState.Empty)
        }
    val shouldDialogShow = remember { mutableStateOf(false) }
    val paymentHistory = remember { mutableStateOf(PaymentHistory.defaultData()) }

    PrepareScreen(
        onPreview = { paymentHistoryState = mutableStateOf(PaymentHistoryState.showPreviewList()) },
        onViewModel = {
            val historyViewModel: HistoryViewModel = hiltViewModel()
            paymentHistoryState = historyViewModel.state.collectAsState()
            historyViewModel.getListOfPayments()
        },
        onDialog = {
            if (shouldDialogShow.value) {
                ViewPaymentHistoryDialog(paymentHistory.value) {
                    shouldDialogShow.value = false
                }
            }
        },
        loadScreen = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { HistoryTopBar(navigate) },
                content = { paddingValues ->
                    when (val state = paymentHistoryState.value) {
                        PaymentHistoryState.Empty -> {} // Do nothing
                        is PaymentHistoryState.ShowList -> {
                            paddingValues.HistoryContent(state.list) { item ->
                                shouldDialogShow.value = true
                                paymentHistory.value = item
                            }
                        }
                    }
                },
            )
        },
    )
}

@Composable
private fun PaddingValues.HistoryContent(
    listOfPayment: List<PaymentHistory>,
    onView: (paymentHistory: PaymentHistory) -> Unit,
) {
    LazyColumn(
        modifier =
            Modifier
                .padding(top = calculateTopPadding())
                .fillMaxWidth(),
    ) {
        items(listOfPayment) { HistoryItem(it, onView) }
    }
}

@Composable
private fun HistoryItem(
    paymentHistory: PaymentHistory,
    onView: (paymentHistory: PaymentHistory) -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onView(paymentHistory) },
    ) {
        HistorySummary(paymentHistory, Modifier.weight(2.5F))
        Spacer(Modifier.weight(1F))
        Image(
            modifier =
                Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1F)
                    .height(70.dp)
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(16.dp)),
            painter = painterResource(R.drawable.sample_receipt_image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Composable
fun HistorySummary(
    paymentHistory: PaymentHistory,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier.padding(16.dp),
    ) {
        Text(text = paymentHistory.timestamp, style = labelTextStyle)
        Spacer(Modifier.padding(8.dp))
        Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxWidth()) {
            Text(text = paymentHistory.amount.floatToCurrency(), style = normalTextStyle)
            Spacer(Modifier.weight(1F))
            Text(text = "Tip: ${paymentHistory.tip.floatToCurrency()}", style = subtitleTextStyle)
        }
    }
}
