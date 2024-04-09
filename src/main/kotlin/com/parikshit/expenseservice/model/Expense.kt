package com.parikshit.expenseservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Document(collection = "expenses")
data class Expense(
    val category: String,
    val description: String,
    val amount: Double,
    val date: LocalDate,
) {
    @Id
    lateinit var id: String
}

data class ExpenseRequest(
    val category: String,
    val description: String = "",
    val amount: Double,
    val date: String,
) {
    fun toExpense(): Expense {
        return Expense(
            category = category,
            description = description,
            amount = amount,
            date =
                LocalDate.parse(
                    date,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                ),
        )
    }
}

typealias ExpenseId = String
