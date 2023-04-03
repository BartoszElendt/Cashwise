package com.company.cashwise.api.budgets;

import com.company.cashwise.domain.budgets.Budget;

import java.math.BigDecimal;

record BudgetDto(String budgetId, String name, String type, BigDecimal cap) {
    static BudgetDto fromDomain(Budget budget) {
        return new BudgetDto(budget.budgetId().value(), budget.name(), budget.type().name(), budget.maxAmount());
    }
}
