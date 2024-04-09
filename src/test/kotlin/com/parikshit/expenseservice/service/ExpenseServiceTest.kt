package com.parikshit.expenseservice.service

import com.parikshit.expenseservice.model.Expense
import com.parikshit.expenseservice.model.ExpenseRequest
import com.parikshit.expenseservice.repository.ExpenseRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toMono
import reactor.test.StepVerifier
import java.time.LocalDate

class ExpenseServiceTest {
    private val expenseRepository = mockk<ExpenseRepository>()
    private val expenseService = ExpenseService(expenseRepository)

    @Test
    fun `should add expense`() {
        val expenseRequest = ExpenseRequest(category = "Shopping", amount = 200.8, date = "14-04-2024")
        val expectedExpense = Expense(category = "Shopping", amount = 200.8, date = LocalDate.of(2024, 4, 14), description = "")

        every { expenseRepository.save(any()) } returns expectedExpense.toMono()

        val result = expenseService.addExpense(expenseRequest)

        verify(exactly = 1) { expenseRepository.save(any()) }

        StepVerifier.create(result).consumeNextWith {
            it as Expense
            assertEquals(it, expectedExpense)
        }.verifyComplete()
    }

    @Test
    fun `should fetches all the expenses`() {
        val expectedExpense = Expense(category = "Shopping", amount = 200.8, date = LocalDate.of(2024, 4, 14), description = "")
        val otherExpense = Expense(category = "Travelling", amount = 250.8, date = LocalDate.of(2024, 4, 15), description = "")
        every { expenseRepository.findAll() } returns Flux.just(expectedExpense, otherExpense)

        val result = expenseService.getAllExpense()

        verify(exactly = 1) { expenseRepository.findAll() }

        StepVerifier.create(result)
            .expectNext(expectedExpense)
            .expectNext(otherExpense)
            .verifyComplete()
    }
}
