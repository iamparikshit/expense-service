package com.parikshit.expenseservice.controller

import com.parikshit.expenseservice.service.ExpenseService
import io.mockk.mockk
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class ControllerConfigTest {
    @Bean
    fun expenseService() = mockk<ExpenseService>()
}
