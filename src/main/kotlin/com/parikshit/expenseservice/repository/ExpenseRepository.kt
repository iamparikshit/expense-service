package com.parikshit.expenseservice.repository

import com.parikshit.expenseservice.model.Expense
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ExpenseRepository : ReactiveMongoRepository<Expense, String>
