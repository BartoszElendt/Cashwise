package com.company.cashwise.api.budgets;

import com.company.cashwise.domain.budgets.BudgetId;
import com.company.cashwise.domain.budgets.BudgetService;
import com.company.cashwise.domain.budgettypes.BudgetType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.function.Supplier;

/**
 * Class that represents incoming requests when registering new budgets.
 * There is no BudgetId here, so we can generate it in application in {@link BudgetService} using {@link Supplier< BudgetId >}.
 *
 * @param type {@link BudgetType}
 * @param name Budget name
 * @param cap  Budget max value as {@link BigDecimal }
 */
record RegisterBudgetRequest(
        // Annotation used for validation, ensures that budget type is not null.
        @NotNull(message = "Budget type cannot be null")
        BudgetType type,

        // Annotation used for validation, ensures that budget name is not blank(only spaces) or empty.
        @NotBlank(message = "Budget name cannot be blank")
        // Annotation used for validation, ensures that budget name is not null.
        @NotNull(message = "Budget name cannot be null")
        String name,

        // Annotation used for validation, ensures that cap is not null.
        @NotNull(message = "Cap cannot be null")
        // Annotation used for validation, cap must be positive.
        @Positive(message = "Cap cannot be negative or zero")
        BigDecimal cap
) {
}
