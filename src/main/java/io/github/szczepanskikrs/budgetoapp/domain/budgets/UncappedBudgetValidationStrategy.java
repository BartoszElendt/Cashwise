package io.github.szczepanskikrs.budgetoapp.domain.budgets;

import io.github.szczepanskikrs.budgetoapp.domain.budgettypes.BudgetType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class UncappedBudgetValidationStrategy implements BudgetValidationStrategy {
    @Override
    public boolean supports(BudgetType budgetType) {
        return budgetType == BudgetType.UNCAPPED;
    }

    @Override
    public boolean validateBudgetInBounds(BigDecimal max, BigDecimal current, BigDecimal requested) {
        return true;
    }
}
