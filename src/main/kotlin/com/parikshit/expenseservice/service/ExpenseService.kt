package com.parikshit.expenseservice.service

import com.parikshit.expenseservice.model.Expense
import com.parikshit.expenseservice.model.ExpenseRequest
import com.parikshit.expenseservice.repository.ExpenseRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ExpenseService(
    private val expenseRepository: ExpenseRepository,
) {
    fun addExpense(expense: ExpenseRequest): Mono<Expense> {
        return expenseRepository.save(expense.toExpense())
    }
}
