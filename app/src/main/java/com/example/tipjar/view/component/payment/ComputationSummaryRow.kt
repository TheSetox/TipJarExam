package com.example.tipjar.view.component.payment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipjar.view.labelTextStyle
import com.example.tipjar.view.normalTextStyle
import com.example.tipjar.view.screen.PaymentScreen

@Preview(showBackground = true)
@Composable
fun ComputationSummaryRowPreview() {
    ComputationSummaryRow("$100", "$10")
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenForComputationSummaryPreview() {
    PaymentScreen()
}

@Composable
fun ComputationSummaryRow(
    totalTip: String,
    totalTipPerPerson: String,
) {
    Column {
        val modifier = Modifier.padding(8.dp)
        val textModifier = Modifier.weight(1F)
        TotalTipText(textModifier, totalTip)
        Spacer(modifier)
        PerPersonText(textModifier, totalTipPerPerson)
        Spacer(modifier)
    }
}

@Composable
private fun TotalTipText(
    modifier: Modifier,
    totalTip: String,
) {
    Row {
        Text(text = "Total Tip", style = labelTextStyle)
        Spacer(modifier)
        Text(text = totalTip, style = labelTextStyle)
    }
}

@Composable
fun PerPersonText(
    modifier: Modifier,
    totalTipPerPerson: String,
) {
    Row {
        Text(text = "Per Person", style = normalTextStyle)
        Spacer(modifier)
        Text(text = totalTipPerPerson, style = normalTextStyle)
    }
}
