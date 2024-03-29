package com.example.tipjar.view.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tipjar.view.screen.PaymentScreen

@Preview(
    showBackground = true,
    device = "spec:width=375px,height=812px,dpi=160",
    name = "figma_spec",
)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen()
}

@Preview(
    showBackground = true,
    name = "main",
)
@Composable
fun PaymentScreenPreview2() {
    PaymentScreen()
}
