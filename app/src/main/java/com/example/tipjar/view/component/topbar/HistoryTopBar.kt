package com.example.tipjar.view.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tipjar.R
import com.example.tipjar.view.labelTextStyle

@Composable
fun HistoryTopBar(navigate: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        val modifier = Modifier.padding(8.dp)
        Text(modifier = modifier, text = "SAVED PAYMENTS", style = labelTextStyle)
        BackButton(modifier, navigate)
        HorizontalDivider(
            modifier = Modifier.align(Alignment.BottomCenter),
            thickness = 1.dp,
            color = Color(0xffebebeb),
        )
    }
}

@Composable
private fun BoxScope.BackButton(
    modifier: Modifier,
    navigate: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .size(50.dp)
                .align(Alignment.CenterStart)
                .clickable { navigate() },
    ) {
        Image(
            painter = painterResource(R.drawable.button_back),
            contentDescription = "Payment History",
        )
    }
}
