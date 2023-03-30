package com.company.cashwise.domain.budgets;

/**
 * Value object that stores value of budgetId. It can be any type as long as its String.
 * In reality, we use UUID from Supplier<BudgetId> converted to String.
 * @param value
 */
public record BudgetId(String value) {
    public static BudgetId ofValue(String budgetId) {
        return new BudgetId(budgetId);
    }
}
