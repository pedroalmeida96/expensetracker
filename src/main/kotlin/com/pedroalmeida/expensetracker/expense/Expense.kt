package com.pedroalmeida.expensetracker.expense

import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "expenses")
@Entity
class Expense(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var description: String,
    var category: Category? = Category.OTHERS,
    var amount: Int,
    var date: LocalDate? = LocalDate.now()
)