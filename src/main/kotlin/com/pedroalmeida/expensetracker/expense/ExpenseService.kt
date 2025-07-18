package com.pedroalmeida.expensetracker.expense

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

private const val EXPENSE_NOT_FOUND = "Expense not found"

@Service
class ExpenseService(
    private val expenseRepository: ExpenseRepository,
    private val expenseFilterHandler: ExpenseFilterHandler
) {

    fun getAllExpenses() = expenseRepository.findAll();

    fun getExpense(id: Long): Expense = expenseRepository.findById(id)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, EXPENSE_NOT_FOUND) }

    fun getExpensesFiltered(filters: Map<String, Any>): List<Expense> {
        val convertedFilters = expenseFilterHandler.handle(filters);
        return expenseRepository.findExpensesByFilters(convertedFilters)
    }

    fun saveExpense(expense: Expense): Expense = expenseRepository.save(expense)

    fun editExpense(expense: Expense): Expense {
        var dbExpense = expenseRepository.findById(expense.id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, EXPENSE_NOT_FOUND) }

        //expenses db validations compared with edited expense

        return expenseRepository.save(expense)
    }

    fun deleteExpense(id: Long) {
        val expense: Expense = expenseRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, EXPENSE_NOT_FOUND) }

        return expenseRepository.delete(expense);
    }
}


