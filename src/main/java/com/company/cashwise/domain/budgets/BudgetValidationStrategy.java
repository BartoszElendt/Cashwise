package com.company.cashwise.domain.budgets;

import com.company.cashwise.domain.budgettypes.BudgetType;

import java.math.BigDecimal;

public interface BudgetValidationStrategy {
    boolean supports(BudgetType budgetType);
    boolean validateBudgetInBounds(BigDecimal max, BigDecimal current, BigDecimal requested);
}
