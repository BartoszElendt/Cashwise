package io.github.szczepanskikrs.budgetoapp.domain.budgets;

import io.github.szczepanskikrs.budgetoapp.domain.budgettypes.BudgetType;

import java.math.BigDecimal;

public interface BudgetValidationStrategy {
    boolean supports(BudgetType budgetType);
    boolean validateBudgetInBounds(BigDecimal max, BigDecimal current, BigDecimal requested);
}
