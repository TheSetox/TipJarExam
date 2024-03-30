package com.example.tipjar.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.tipjar.R
import com.example.tipjar.model.entity.PaymentHistory
import com.example.tipjar.view.component.topbar.HistoryTopBar
import com.example.tipjar.view.labelTextStyle
import com.example.tipjar.view.normalTextStyle
import com.example.tipjar.view.subtitleTextStyle

@Preview
@Composable
fun HistoryScreenPreview() {
    HistoryScreen()
}

@Preview(showBackground = true)
@Composable
fun ViewPaymentHistoryScreenPreview() {
    ViewPaymentHistoryDialog {}
}

@Composable
fun ViewPaymentHistoryDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties =
            DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            ),
    ) {
        Column {
            Image(
                modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                painter = painterResource(R.drawable.sample_receipt_image),
                contentDescription = null,
            )
            Spacer(Modifier.size(12.dp))
            Box(Modifier.clip(RoundedCornerShape(16.dp)).background(Color.White)) {
                HistorySummary()
            }
        }
    }
}

// TODO Remove when not needed anymore.
internal val defaultPaymentHistory =
    PaymentHistory(
        timestamp = "",
        amount = 100F,
        tip = 10F,
        image = "",
    )

@Composable
fun HistoryScreen(navigate: () -> Unit = {}) {
    // TODO Remove once database is connected or debug variant is setup.
    val listOfPayment = listOf(defaultPaymentHistory, defaultPaymentHistory)
    val shouldView = remember { mutableStateOf(false) }
    val shouldDismiss = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { HistoryTopBar(navigate) },
        content = {
            it.HistoryContent(listOfPayment) {
                shouldView.value = true
                shouldDismiss.value = false
            }
        },
    )
    if (shouldView.value) {
        ViewPaymentHistoryDialog {
            shouldView.value = false
            shouldDismiss.value = true
        }
    }
}

@Composable
private fun PaddingValues.HistoryContent(
    listOfPayment: List<PaymentHistory>,
    onView: () -> Unit,
) {
    LazyColumn(
        modifier =
            Modifier
                .padding(top = calculateTopPadding())
                .fillMaxWidth(),
    ) {
        items(listOfPayment) { HistoryItem(onView) }
    }
}

@Composable
private fun HistoryItem(onView: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onView() },
    ) {
        HistorySummary(Modifier.weight(2.5F))
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
fun HistorySummary(modifier: Modifier = Modifier) {
    Column(
        modifier.padding(16.dp),
    ) {
        Text(text = "2021 January 21", style = labelTextStyle)
        Spacer(Modifier.padding(8.dp))
        Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxWidth()) {
            Text(text = "$205.53", style = normalTextStyle)
            Spacer(Modifier.weight(1F))
            Text(text = "Tip: $20.52", style = subtitleTextStyle)
        }
    }
}
