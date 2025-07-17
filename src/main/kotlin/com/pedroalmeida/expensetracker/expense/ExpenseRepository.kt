package com.pedroalmeida.expensetracker.expense

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface ExpenseRepository : JpaRepository<Expense, Long> {

    @Query(
        """
    SELECT e FROM Expense e 
    WHERE (:#{#filters['date']} IS NULL OR e.date = :#{#filters['date']})
      AND (:#{#filters['category']} IS NULL OR e.category = :#{#filters['category']})
      AND (:#{#filters['description']} IS NULL OR e.description LIKE CONCAT('%', :#{#filters['description']}, '%'))
    """
    )
    fun findExpensesByFilters(@Param("filters") filters: Map<String, Any>): List<Expense>

}