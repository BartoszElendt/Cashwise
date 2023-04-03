package com.company.cashwise.domain.budgets;

import com.company.cashwise.domain.budgettypes.BudgetType;
import com.company.cashwise.domain.users.UserId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public record Budget(
        @Id
        BudgetId budgetId,
        UserId userId,
        String name,
        BigDecimal maxAmount,
        BudgetType type
) {
}
