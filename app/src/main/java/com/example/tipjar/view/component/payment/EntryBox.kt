package com.example.tipjar.view.component.payment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipjar.view.editTextStyle
import com.example.tipjar.view.hintTextStyle
import com.example.tipjar.view.labelTextStyle
import com.example.tipjar.view.normalTextStyle
import com.example.tipjar.view.screen.PaymentScreen

@Preview(showBackground = true)
@Composable
fun EntryBoxPreview() {
    Column {
        EntryBox(
            label = "Enter amount",
            hintText = "100.00",
            helperText = "$",
            helperPosition = Position.LEFT,
        )
        EntryBox(
            label = "% TIP",
            hintText = "10.00",
            helperText = "%",
            helperPosition = Position.RIGHT,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenForEntryBoxPreview() {
    PaymentScreen()
}

@Composable
fun EntryBox(
    label: String,
    hintText: String,
    helperText: String,
    helperPosition: Position,
) {
    Column {
        Text(text = label, style = labelTextStyle)
        Spacer(Modifier.padding(8.dp))
        PaymentTextField(
            hintText = hintText,
            helperText = helperText,
            helperPosition = helperPosition,
        )
    }
}

@Composable
private fun PaymentTextField(
    hintText: String,
    helperText: String,
    helperPosition: Position,
) {
    var text by remember { mutableStateOf("") }
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            Modifier
                .height(85.dp)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, Color(0xffcbcbcb)),
                    shape = RoundedCornerShape(16.dp),
                ),
    ) {
        HelperText(helperText, helperPosition)
        Text(text = hintText, style = hintTextStyle)
        BasicTextField(
            singleLine = true,
            modifier = Modifier.padding(start = 20.dp),
            textStyle = editTextStyle,
            value = text,
            onValueChange = {
                text = it
            },
        )
    }
}

@Composable
private fun BoxScope.HelperText(
    helperText: String,
    helperPosition: Position,
) {
    val alignment =
        when (helperPosition) {
            Position.LEFT -> Alignment.CenterStart
            Position.RIGHT -> Alignment.CenterEnd
        }
    Text(
        text = helperText,
        modifier =
            Modifier
                .padding(16.dp)
                .align(alignment),
        style = normalTextStyle,
    )
}

enum class Position { LEFT, RIGHT }
