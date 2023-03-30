package io.github.szczepanskikrs.budgetoapp.domain.budgets;

import io.github.szczepanskikrs.budgetoapp.domain.budgettypes.BudgetType;
import io.github.szczepanskikrs.budgetoapp.domain.users.UserId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Class that represents budgets
 * {@link Document} is annotation that is used as meta information for SpringData. Thanks to that and {@link Id} annotations
 * you can user `save`, `findById` etc. From SpringData Repositories and it works out of the box.
 * @param budgetId
 * @param userId
 * @param name
 * @param maxAmount
 * @param type
 */
@Document
public record Budget(
        @Id
        BudgetId budgetId,
        UserId userId,
        String name,
        BigDecimal maxAmount,
        // Enum representing budget type
        BudgetType type
) {
}
