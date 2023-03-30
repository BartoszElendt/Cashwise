package com.company.cashwise.infrastructure.config;

import com.company.cashwise.domain.budgets.BudgetId;
import com.company.cashwise.domain.expenses.ExpenseId;
import com.company.cashwise.domain.users.UserId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Supplier;

@Configuration
class IdSupplierConfig {

    @Bean
    public Supplier<ExpenseId> expenseIdSupplier() {
        return () -> new ExpenseId(UUID.randomUUID().toString());
    }

    @Bean
    public Supplier<UserId> userIdSupplier() {
        return () -> new UserId(UUID.randomUUID().toString());
    }

    @Bean
    public Supplier<BudgetId> budgetIdSupplier() {
        return () -> BudgetId.ofValue(UUID.randomUUID().toString());
    }
}
