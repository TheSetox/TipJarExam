package com.example.tipjar.view.component.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.tipjar.R
import com.example.tipjar.model.entity.PaymentHistory
import com.example.tipjar.view.buttonTextStyle
import com.example.tipjar.view.screen.HistorySummary

@Preview(showBackground = true)
@Composable
fun ViewPaymentHistoryDialogPreview() {
    ViewPaymentHistoryDialog(PaymentHistory(timestamp = "", amount = 0F, tip = 0F, image = ""), {})
}

@Composable
fun ViewPaymentHistoryDialog(
    paymentHistory: PaymentHistory,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties =
            DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            ),
    ) {
        val modifier = Modifier.clip(RoundedCornerShape(16.dp))
        Column {
            Image(
                modifier = modifier,
                painter = painterResource(R.drawable.sample_receipt_image),
                contentDescription = null,
            )
            Spacer(Modifier.size(12.dp))
            Box(modifier.background(Color.White)) {
                HistorySummary(paymentHistory)
            }
            Spacer(Modifier.size(12.dp))
            DeletePaymentButton {}
        }
    }
}

@Composable
private fun DeletePaymentButton(onDelete: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Red,
                    shape = RoundedCornerShape(16.dp),
                )
                .clickable { onDelete() },
    ) {
        Text(
            text = "Delete Payment",
            style = buttonTextStyle,
            modifier = Modifier.padding(vertical = 16.dp),
        )
    }
}
