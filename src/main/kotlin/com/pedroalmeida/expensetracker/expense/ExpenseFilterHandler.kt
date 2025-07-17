package com.pedroalmeida.expensetracker.expense

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ExpenseFilterHandler {
    fun handle(filters: Map<String, Any>): Map<String, Any> {
        // Map through the filters and process each key-value pair
        return filters.mapValues { (key, value) ->
            when (key) {
                "date" -> if (value is String && value.isNotBlank()) LocalDate.parse(value) else value
                "description" -> if (value is String) value.trim() else value
                "category" -> if (value is String) Category.valueOf(value) else value
                else -> value
            }
        }
    }
}

