package com.company.cashwise.domain.expenses;

import com.company.cashwise.domain.budgets.BudgetId;
import com.company.cashwise.domain.users.UserId;
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
