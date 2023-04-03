package com.company.cashwise.domain.budgets;

public record BudgetId(String value) {
    public static BudgetId ofValue(String budgetId) {
        return new BudgetId(budgetId);
    }
}
