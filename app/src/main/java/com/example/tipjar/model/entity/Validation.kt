package com.example.tipjar.model.entity

data class Validation(
    val amountValidation: ValidateResult,
    val countPerPersonValidation: ValidateResult,
    val tipPercentageValidation: ValidateResult,
)
