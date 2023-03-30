package com.company.cashwise.api.budgets;

import com.company.cashwise.domain.budgets.BudgetState;

import java.math.BigDecimal;

public record BudgetStateDto(
        String budgetId,
        Integer totalExpensesCount,
        BigDecimal maxBudgetSize,
        BigDecimal budgetLeft,
        BigDecimal expensesSum
) {
    public static BudgetStateDto fromDomain(BudgetState state) {
        return new BudgetStateDto(
                state.budgetId().value(),
                state.totalExpenses(),
                state.cap(),
                state.amountLeft(),
                state.expensesSum()
        );
    }
}
