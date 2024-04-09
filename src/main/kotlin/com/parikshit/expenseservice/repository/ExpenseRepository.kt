package com.parikshit.expenseservice.repository

import com.parikshit.expenseservice.model.Expense
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ExpenseRepository : ReactiveMongoRepository<Expense, String>
