package com.pedroalmeida.expensetracker.expense

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ExpenseService(
    private val expenseRepository: ExpenseRepository,
    private val expenseFilterHandler: ExpenseFilterHandler
) {

    fun getAllExpenses() = expenseRepository.findAll();

    fun getExpense(id: Long): Expense = expenseRepository.findById(id)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found") }

    fun getExpensesFiltered(filters: Map<String, Any>): List<Expense> {
        val convertedFilters = expenseFilterHandler.handle(filters);
        return expenseRepository.findExpensesByFilters(convertedFilters)
    }

    fun saveExpense(expense: Expense): Expense = expenseRepository.save<Expense>(expense)

    fun deleteExpense(id: Long) {
        val expense: Expense = expenseRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found") }

        return expenseRepository.delete(expense);
    }
}


