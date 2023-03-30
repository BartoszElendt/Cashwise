package io.github.szczepanskikrs.budgetoapp.domain.budgets;

import io.github.szczepanskikrs.budgetoapp.domain.budgettypes.BudgetType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class CappedBudgetValidationStrategy implements BudgetValidationStrategy {
    @Override
    public boolean supports(BudgetType budgetType) {
        return budgetType == BudgetType.CAPPED;
    }

    @Override
    public boolean validateBudgetInBounds(BigDecimal max, BigDecimal current, BigDecimal requested) {
        return max.compareTo(current.add(requested)) >= 0;
    }
}
