package io.github.szczepanskikrs.budgetoapp.api.budgets;

import io.github.szczepanskikrs.budgetoapp.domain.budgets.Budget;

import java.math.BigDecimal;
/**
 * Class that is representation of Budget that is retrieved by application clients using REST API /budgets/**
 * Class don't need to be public since we have controller in same package.
 * @param name returned budget name
 * @param budgetId returned budget id
 * @param type returned type of budget
 * @param cap returned max value of budget
 */
record BudgetDto(String budgetId, String name, String type, BigDecimal cap) {
    static BudgetDto fromDomain(Budget budget) {
        return new BudgetDto(budget.budgetId().value(), budget.name(), budget.type().name(), budget.maxAmount());
    }
}
