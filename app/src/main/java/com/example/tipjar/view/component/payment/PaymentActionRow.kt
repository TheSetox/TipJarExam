package com.example.tipjar.view.component.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipjar.R
import com.example.tipjar.view.buttonTextStyle
import com.example.tipjar.view.labelTextStyle
import com.example.tipjar.view.screen.PaymentScreen

@Preview(showBackground = true)
@Composable
fun PaymentActionRowPreview() {
    PaymentActionRow()
}

@Preview(showBackground = true)
@Composable
fun CheckBoxPreview() {
    val modifier = Modifier.padding(20.dp)
    Column(modifier) {
        CheckedBox {}
        Spacer(modifier)
        UnCheckedBox {}
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenForPaymentActionPreview() {
    PaymentScreen()
}

@Composable
fun PaymentActionRow(onSave: (Boolean) -> Unit = {}) {
    val isChecked = rememberSaveable { mutableStateOf(false) }
    Column {
        TakePhotoCheckBox(isChecked = isChecked.value) { isChecked.value = it }
        Spacer(Modifier.padding(12.dp))
        SavePaymentButton(isChecked = isChecked.value, onSave = onSave)
        Spacer(Modifier.padding(8.dp))
    }
}

@Composable
private fun TakePhotoCheckBox(
    isChecked: Boolean,
    onChecked: (Boolean) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        when (isChecked) {
            true -> CheckedBox { onChecked(false) }
            false -> UnCheckedBox { onChecked(true) }
        }
        Spacer(Modifier.padding(8.dp))
        Text(text = "Take photo of receipt", style = labelTextStyle)
    }
}

@Composable
private fun SavePaymentButton(
    isChecked: Boolean,
    onSave: (Boolean) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    brush =
                        Brush.linearGradient(
                            colors = listOf(Color(0xfff27a0a), Color(0xffd26e11)),
                        ),
                    shape = RoundedCornerShape(16.dp),
                )
                .clickable { onSave(isChecked) },
    ) {
        Text(
            text = "Save Payment",
            style = buttonTextStyle,
            modifier = Modifier.padding(vertical = 16.dp),
        )
    }
}

@Composable
private fun UnCheckedBox(onClicked: () -> Unit) {
    Box(
        Modifier.clickable { onClicked() },
        contentAlignment = Alignment.Center,
    ) {
        PaymentActionImage(R.drawable.unchecked_box)
        PaymentActionImage(R.drawable.image_uncheck)
    }
}

@Composable
private fun CheckedBox(onClicked: () -> Unit) {
    Box(
        Modifier.clickable { onClicked() },
        contentAlignment = Alignment.Center,
    ) {
        PaymentActionImage(R.drawable.check_box)
        PaymentActionImage(R.drawable.image_checked)
    }
}

@Composable
private fun PaymentActionImage(id: Int) {
    Image(painter = painterResource(id), contentDescription = null)
}
