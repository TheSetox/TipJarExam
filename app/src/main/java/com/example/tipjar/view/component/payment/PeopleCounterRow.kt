package com.example.tipjar.view.component.payment

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipjar.view.controllerTextStyle
import com.example.tipjar.view.editTextStyle
import com.example.tipjar.view.labelTextStyle
import com.example.tipjar.view.screen.PaymentScreen

@Preview(showBackground = true)
@Composable
fun PeopleCounterRowPreview() {
    PeopleCounterRow()
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen()
}

@Composable
fun PeopleCounterRow() {
    Column {
        Text(text = "How many people?", style = labelTextStyle)
        Spacer(Modifier.padding(8.dp))
        CounterRow()
    }
}

@Composable
private fun CounterRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val circleModifier =
            Modifier
                .size(75.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xffd2d2d2),
                    shape = CircleShape,
                )
        val spacerModifier = Modifier.weight(1F)

        CircleActionButton(text = "+", modifier = circleModifier)
        Spacer(spacerModifier)
        Text(text = "1", style = editTextStyle)
        Spacer(spacerModifier)
        CircleActionButton(text = "-", modifier = circleModifier)
    }
}

@Composable
private fun CircleActionButton(
    text: String,
    modifier: Modifier,
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Text(text = text, style = controllerTextStyle)
    }
}
