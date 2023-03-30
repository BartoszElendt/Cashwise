package io.github.szczepanskikrs.budgetoapp.domain.expenses;

import io.github.szczepanskikrs.budgetoapp.domain.budgets.BudgetId;
import io.github.szczepanskikrs.budgetoapp.domain.users.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

public interface ExpensesService {
    Expense registerNewExpense(String title, BigDecimal amount, Instant registrationTimestamp, UserId userId, BudgetId budgetId);

    Optional<Expense> getExpenseById(ExpenseId expenseId, UserId userId);

    Page<Expense> getExpensesPage(PageRequest pageRequest, UserId userId);
}
