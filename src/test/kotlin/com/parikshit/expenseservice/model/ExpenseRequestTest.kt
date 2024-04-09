package com.parikshit.expenseservice.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ExpenseRequestTest {
    @Test
    fun `should return expense from expense request`() {
        val expenseRequest = ExpenseRequest(category = "Shopping", amount = 200.8, date = "14-04-2024")
        val expectedExpense = Expense(category = "Shopping", amount = 200.8, date = LocalDate.of(2024, 4, 14), description = "")

        val result = expenseRequest.toExpense()

        assertEquals(result, expectedExpense)
    }
}
