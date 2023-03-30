package io.github.szczepanskikrs.budgetoapp.infrastructure;

import io.github.szczepanskikrs.budgetoapp.domain.budgets.BudgetId;
import io.github.szczepanskikrs.budgetoapp.domain.expenses.Expense;
import io.github.szczepanskikrs.budgetoapp.domain.expenses.ExpenseId;
import io.github.szczepanskikrs.budgetoapp.domain.users.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends MongoRepository<Expense, ExpenseId> {
    Optional<Expense> findExpenseByExpenseIdAndUserId(ExpenseId expenseId, UserId userId);

    Page<Expense> findExpensesByUserId(UserId userId, Pageable pageable);

    List<Expense> findExpensesByUserIdAndBudgetId(UserId userId, BudgetId budgetId);
}
