package io.github.szczepanskikrs.budgetoapp.domain.expenses;

import io.github.szczepanskikrs.budgetoapp.domain.budgets.BudgetId;
import io.github.szczepanskikrs.budgetoapp.domain.users.UserId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document
public record Expense(
        @Id
        ExpenseId expenseId,
        String title,
        BigDecimal amount,
        UserId userId,
        BudgetId budgetId,
        Instant budgetRegistrationTimestamp
) {
    public static Expense of(
            ExpenseId expenseId,
            String title,
            BigDecimal amount,
            UserId userId,
            BudgetId budgetId,
            Instant budgetRegistrationTimestamp
    ) {
        return new Expense(expenseId, title, amount, userId, budgetId, budgetRegistrationTimestamp);
    }
}
