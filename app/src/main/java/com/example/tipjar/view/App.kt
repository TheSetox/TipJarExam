package com.example.tipjar.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.tipjar.R
import com.example.tipjar.view.screen.PaymentScreen

@Composable
fun App() {
    MaterialTheme { PaymentScreen() }
}

@Preview(showBackground = true)
@Composable
fun TextStylePreview() {
    Column {
        Text(text = "Label Text Style", style = labelTextStyle)
        Text(text = "Normal Text Style", style = normalTextStyle)
        Text(text = "Subtitle Text Style", style = subtitleTextStyle)
        Text(text = "Hint Text Style", style = hintTextStyle)
        Text(text = "Edit Text Style", style = editTextStyle)
        Text(text = "Controller Style", style = controllerTextStyle)
        Row(Modifier.background(Color.Black)) {
            Text(text = "Button Text Style", style = buttonTextStyle)
        }
    }
}

val labelTextStyle =
    TextStyle(
        fontWeight = FontWeight(500),
        lineHeight = 16.sp,
        fontSize = 14.sp,
        color = Color(0xff000000),
    )

val subtitleTextStyle =
    TextStyle(
        fontWeight = FontWeight(500),
        lineHeight = 16.sp,
        fontSize = 14.sp,
        color = Color(0xff7d7d7d),
    )

val normalTextStyle =
    TextStyle(
        fontWeight = FontWeight(500),
        lineHeight = 27.sp,
        fontSize = 24.sp,
        color = Color(0xff000000),
    )

val hintTextStyle =
    TextStyle(
        fontWeight = FontWeight(500),
        lineHeight = 38.sp,
        fontSize = 36.sp,
        color = Color(0xffdadada),
    )

val editTextStyle =
    TextStyle(
        fontWeight = FontWeight(500),
        lineHeight = 38.sp,
        fontSize = 36.sp,
        color = Color(0xff000000),
        textAlign = TextAlign.Center,
    )

val controllerTextStyle =
    TextStyle(
        fontWeight = FontWeight(500),
        lineHeight = 38.sp,
        fontSize = 36.sp,
        color = Color(0xfff27a0a),
        textAlign = TextAlign.Center,
    )

val buttonTextStyle =
    TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_pro_font)),
        fontWeight = FontWeight(800),
        lineHeight = 16.sp,
        fontSize = 14.sp,
        color = Color(0xffffffff),
        textAlign = TextAlign.Center,
    )

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen()
}
