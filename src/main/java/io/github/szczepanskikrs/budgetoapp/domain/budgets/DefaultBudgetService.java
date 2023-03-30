package io.github.szczepanskikrs.budgetoapp.domain.budgets;

import io.github.szczepanskikrs.budgetoapp.domain.budgettypes.BudgetType;
import io.github.szczepanskikrs.budgetoapp.domain.expenses.Expense;
import io.github.szczepanskikrs.budgetoapp.domain.users.UserId;
import io.github.szczepanskikrs.budgetoapp.infrastructure.BudgetRepository;
import io.github.szczepanskikrs.budgetoapp.infrastructure.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Default implementation of {@link BudgetService} when you inject {@link BudgetService} to other classes Spring will
 * inject instance of this class since it's only {@link Component} of that type. We use {@link Service} here,
 * but as you remember its just alias on Component annotation.
 */
@Service
class DefaultBudgetService implements BudgetService {
    // Repository used to store budgets
    private final BudgetRepository repository;
    // Expenses repository used to query expenses
    private final ExpenseRepository expenseRepository;
    // Supplier that is used to get BudgetIds, can be useful for test purposes, or can be used to generate id's with
    // different logic
    private final Supplier<BudgetId> budgetIdSupplier;

    // Constructor
    public DefaultBudgetService(BudgetRepository repository,
                                ExpenseRepository expenseRepository, Supplier<BudgetId> budgetIdSupplier
    ) {
        this.repository = repository;
        this.expenseRepository = expenseRepository;
        this.budgetIdSupplier = budgetIdSupplier;
    }

    @Override
    // We crete budget here, use supplier to get BudgetId and then save it to repository
    public Budget registerNewBudget(String name, UserId userId, BigDecimal cap, BudgetType budgetType) {
        Budget budget = new Budget(budgetIdSupplier.get(), userId, name, cap, budgetType);
        return repository.save(budget);
    }

    @Override
    // We return Optional of budgets from repository, if it's not found repository will return Optional.EMPTY
    public Optional<Budget> getBudget(BudgetId budgetId, UserId userId) {
        return repository.findBudgetByBudgetIdAndUserId(budgetId, userId);
    }

    @Override
    // Check if budget is present, if present update it, if not present add it. To be idempotent we need to either update or add if not present
    public Optional<Budget> updateBudget(BudgetId budgetId, String name, UserId userId, BigDecimal cap, BudgetType budgetType) {
        //Create budget with new data
        Budget updatedBudget = new Budget(budgetId, userId, name, cap, budgetType);
        return Optional.of(
                // Check if budget exists
                repository.findBudgetByBudgetIdAndUserId(budgetId, userId)
                        // If it exists save new version
                        // save will update document if it exists
                        .map(repoBudget -> repository.save(updatedBudget))
                        // If not present insert new budget
                        // insert will only add new document or throw exception if budget with given id exists
                        .orElseGet(() -> repository.insert(updatedBudget))
        );
    }

    @Override
    public void deleteBudget(BudgetId budgetId, UserId userId) {
        // Removes budget from repository
        repository.deleteBudgetByBudgetIdAndUserId(budgetId, userId);
    }

    @Override
    // Get user budgets by UserId and pageRequest (object that stores information about page and size).
    // It returns Page object with found elements, or empty page object if nothing was found.
    public Page<Budget> getBudgets(PageRequest pageRequest, UserId userId) {
        return repository.getBudgetsByUserId(userId, pageRequest);
    }

    @Override
    public Optional<BudgetState> getBudgetState(BudgetId budgetId, UserId userId) {
        // Gets budget or empty optional
        Optional<Budget> budget = repository.findBudgetByBudgetIdAndUserId(budgetId, userId);

        // If budget is not empty it will try to get all expenses
        Optional<List<Expense>> expenses = budget
                .map(Budget::budgetId)
                .map(id -> expenseRepository.findExpensesByUserIdAndBudgetId(userId, id));

        // If expenses are not empty they will be counted or 0 will be returned
        Integer totalExpensesFound = calculateCountOf(expenses);

        // If expenses are not empty they will be summed or 0 will be returned
        BigDecimal totalExpensesSum = calculateSumOf(expenses);

        // Results of all lazy calculations are assembled to BudgetState, if budget is not found it will return Optional.EMPTY
        return budget.map(foundBudget -> new BudgetState(
                        foundBudget.budgetId(),
                        foundBudget.maxAmount(),
                        totalExpensesFound,
                        totalExpensesSum,
                        calculateAmountLeft(foundBudget.maxAmount(), totalExpensesSum)
                )
        );
    }

    // Calculates total expenses count or return zero when no expenses found
    private static Integer calculateCountOf(Optional<List<Expense>> expenses) {
        return expenses.map(List::size).orElse(NO_EXPENSES_COUNT);
    }

    // Calculates total expenses sum or return zero when no expenses found
    private static BigDecimal calculateSumOf(Optional<List<Expense>> expenses) {
        return expenses.map(foundExpenses -> foundExpenses.stream()
                        .map(Expense::amount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
                .orElse(NO_EXPENSES_SUM);
    }

    // Calculates amount left in budget
    private BigDecimal calculateAmountLeft(BigDecimal max, BigDecimal currentState) {
        return max.subtract(currentState);
    }

    private static final Integer NO_EXPENSES_COUNT = 0;
    private static final BigDecimal NO_EXPENSES_SUM = BigDecimal.ZERO;
}
