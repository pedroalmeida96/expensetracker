package com.pedroalmeida.expensetracker.expense

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/expense")
class ExpenseController(private val expenseService: ExpenseService) {

    @GetMapping
    fun getAllExpenses() = expenseService.getAllExpenses()

    @GetMapping("/{id}")
    fun getExpense(@PathVariable("id") id: Long): Expense = expenseService.getExpense(id);

    @GetMapping("/filter")
    fun getExpensesFiltered(@RequestBody filters: Map<String, Any>) =
        expenseService.getExpensesFiltered(filters)

    @PostMapping
    fun addExpense(@RequestBody expense: Expense) = expenseService.saveExpense(expense)

    @PutMapping
    fun editExpense(@RequestBody expense: Expense) =
        expenseService.editExpense(expense)

    @DeleteMapping("/{id}")
    fun deleteExpense(@PathVariable id: Long) = expenseService.deleteExpense(id)

}