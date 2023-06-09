package com.company.cashwise.api.expenses;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public record RegisterExpenseRequest(
        @NotNull(message = "Expense title cannot be null")
        @NotEmpty(message = "Title cannot be null")
        @Length(
                min = 3,
                max = 200,
                message = "Title cannot be shorter than 3 and longer than 200"
        )
        String title,
        @NotNull(message = "Expense amount cannot be null")
        @Positive(message = "Expense amount cannot be negative or zero")
        BigDecimal amount,
        @NotNull(message = "BudgetId cannot be null")
        @NotBlank(message = "BudgetId cannot be blank")
        String budgetId
) {
}
