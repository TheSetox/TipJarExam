package com.example.tipjar.view.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipjar.R

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    PaymentTopBar()
}

@Composable
fun PaymentTopBar(navigate: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        val modifier = Modifier.padding(8.dp)

        Image(
            modifier = modifier,
            painter = painterResource(R.drawable.tipjar_logo),
            contentDescription = "Tip Jar Logo",
        )
        HistoryButton(modifier, navigate)
    }
}

@Composable
private fun BoxScope.HistoryButton(
    modifier: Modifier,
    navigate: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .size(50.dp)
                .align(Alignment.CenterEnd)
                .clickable { navigate() },
    ) {
        Image(
            painter = painterResource(R.drawable.button_payment_history),
            contentDescription = "Payment History",
        )
    }
}
