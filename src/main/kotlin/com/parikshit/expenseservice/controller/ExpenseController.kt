package com.parikshit.expenseservice.controller

import com.parikshit.expenseservice.model.Expense
import com.parikshit.expenseservice.model.ExpenseId
import com.parikshit.expenseservice.model.ExpenseRequest
import com.parikshit.expenseservice.service.ExpenseService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/expense-service/v1/expense")
class ExpenseController(
    private val expenseService: ExpenseService,
) {
    @PostMapping
    fun addExpense(
        @RequestBody expense: ExpenseRequest,
    ): Mono<ExpenseId> {
        return expenseService.addExpense(expense)
            .map { it.id }
    }

    @GetMapping
    fun getAllExpenses(): Flux<Expense> {
        return expenseService.getAllExpense()
    }
}
