package com.example.tipjar.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipjar.view.component.payment.ComputationSummaryRow
import com.example.tipjar.view.component.payment.EntryBox
import com.example.tipjar.view.component.payment.PaymentActionRow
import com.example.tipjar.view.component.payment.PeopleCounterRow
import com.example.tipjar.view.component.payment.Position
import com.example.tipjar.view.component.topbar.PaymentTopBar

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen()
}

@Composable
fun PaymentScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { PaymentTopBar() },
        content = { it.PaymentContent() },
    )
}

@Composable
private fun PaddingValues.PaymentContent() {
    val spacerModifier = Modifier.padding(16.dp)
    Column(
        modifier =
            Modifier
                .padding(top = calculateTopPadding())
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
    ) {
        EntryBox(
            label = "Enter amount",
            hintText = "100.00",
            helperText = "$",
            helperPosition = Position.LEFT,
        )
        Spacer(spacerModifier)
        PeopleCounterRow()
        Spacer(spacerModifier)
        EntryBox(
            label = "% TIP",
            helperText = "%",
            hintText = "10.00",
            helperPosition = Position.RIGHT,
        )
        Spacer(spacerModifier)
        ComputationSummaryRow()
        Spacer(Modifier.weight(1f))
        PaymentActionRow()
    }
}
