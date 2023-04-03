package com.company.cashwise.domain.budgets;

import com.company.cashwise.domain.budgettypes.BudgetType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class DoubleCappedBudgetValidationStrategy implements BudgetValidationStrategy {
    private static final BigDecimal DOUBLE_BUDGET_MULTIPLIER = BigDecimal.valueOf(2);

    @Override
    public boolean supports(BudgetType budgetType) {
        return budgetType == BudgetType.CAPPED_DOUBLE;
    }

    @Override
    public boolean validateBudgetInBounds(BigDecimal max, BigDecimal current, BigDecimal requested) {
        BigDecimal calculatedMax = max.multiply(DOUBLE_BUDGET_MULTIPLIER);
        return calculatedMax.compareTo(current.add(requested)) >= 0;
    }
}
