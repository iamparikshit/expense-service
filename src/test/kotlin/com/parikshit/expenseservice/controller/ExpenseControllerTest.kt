package com.parikshit.expenseservice.controller

import com.parikshit.expenseservice.model.Expense
import com.parikshit.expenseservice.model.ExpenseId
import com.parikshit.expenseservice.service.ExpenseService
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.kotlin.core.publisher.toMono
import java.time.LocalDate

@WebFluxTest(ExpenseController::class)
@ContextConfiguration(
    classes = [ControllerConfigTest::class],
)
class ExpenseControllerTest(
    @Autowired private val webTestClient: WebTestClient,
) {
    @Autowired
    private lateinit var expenseService: ExpenseService

    @Test
    fun `should call expense service`() {
        val expectedExpense = Expense(category = "Shopping", amount = 200.8, date = LocalDate.of(2024, 4, 14), description = "")
        expectedExpense.id = "EXPENSE_ID"

        every { expenseService.addExpense(any()) } returns expectedExpense.toMono()
        val request =
            """
            {
                "amount": "200.8",
                "category": "Shopping",
                "date": "14-04-2024"
            }    
            """.trimIndent()

        webTestClient.post()
            .uri("/expense-service/v1/expense")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<ExpenseId>()
            .isEqualTo("EXPENSE_ID")

        verify(exactly = 1) { expenseService.addExpense(any()) }
    }
}
