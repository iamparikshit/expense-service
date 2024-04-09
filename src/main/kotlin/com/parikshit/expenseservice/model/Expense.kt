package com.parikshit.expenseservice.model

import java.time.LocalDate

data class Expense(
    val id: String,
    val category: String,
    val description: String = "",
    val amount: Double,
    val date: LocalDate,
)
