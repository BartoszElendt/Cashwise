package com.company.cashwise.api.budgets;

import com.company.cashwise.domain.budgets.BudgetId;
import com.company.cashwise.domain.budgets.BudgetService;
import com.company.cashwise.domain.budgettypes.BudgetType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.function.Supplier;

record RegisterBudgetRequest(
        @NotNull(message = "Budget type cannot be null")
        BudgetType type,
        @NotBlank(message = "Budget name cannot be blank")
        @NotNull(message = "Budget name cannot be null")
        String name,
        @NotNull(message = "Cap cannot be null")
        @Positive(message = "Cap cannot be negative or zero")
        BigDecimal cap
) {
}
