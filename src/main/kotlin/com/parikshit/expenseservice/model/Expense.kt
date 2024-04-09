package com.parikshit.expenseservice.model

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collection = "expenses")
data class Expense(
    val id: String,
    val category: String,
    val description: String = "",
    val amount: Double,
    val date: LocalDate,
)

typealias ExpenseId = String
