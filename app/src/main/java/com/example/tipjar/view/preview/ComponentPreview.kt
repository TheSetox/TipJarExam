package com.example.tipjar.view.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tipjar.view.component.payment.ComputationSummaryRow
import com.example.tipjar.view.component.payment.EntryBox
import com.example.tipjar.view.component.payment.PaymentActionRow
import com.example.tipjar.view.component.payment.PeopleCounterRow
import com.example.tipjar.view.component.payment.Position
import com.example.tipjar.view.component.topbar.HistoryTopBar
import com.example.tipjar.view.component.topbar.PaymentTopBar

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    PaymentTopBar()
}

@Preview(showBackground = true)
@Composable
fun EntryBoxPreview() {
    Column {
        Column {
            EntryBox(
                mainText = "",
                label = "Enter amount",
                hintText = "100.00",
                helperText = "$",
                helperPosition = Position.LEFT,
            )
            EntryBox(
                mainText = "",
                label = "% TIP",
                hintText = "10.00",
                helperText = "%",
                helperPosition = Position.RIGHT,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PeopleCounterRowPreview() {
    PeopleCounterRow(1)
}

@Preview(showBackground = true)
@Composable
fun ComputationSummaryRowPreview() {
    ComputationSummaryRow("", "")
}

@Preview(showBackground = true)
@Composable
fun PaymentActionRowPreview() {
    PaymentActionRow()
}

@Preview(showBackground = true)
@Composable
fun HistoryTopBarPreview() {
    HistoryTopBar()
}
