package com.example.tipjar.view.component.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.tipjar.R
import com.example.tipjar.model.entity.PaymentHistory
import com.example.tipjar.view.screen.HistorySummary

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
        Column {
            Image(
                modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                painter = painterResource(R.drawable.sample_receipt_image),
                contentDescription = null,
            )
            Spacer(Modifier.size(12.dp))
            Box(
                Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White),
            ) {
                HistorySummary(paymentHistory)
            }
        }
    }
}
