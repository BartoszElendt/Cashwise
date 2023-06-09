package com.company.cashwise.api.expenses;

import com.company.cashwise.domain.budgets.BudgetId;
import com.company.cashwise.domain.expenses.Expense;
import com.company.cashwise.domain.expenses.ExpenseId;
import com.company.cashwise.domain.expenses.ExpensesService;
import com.company.cashwise.domain.users.BudgetAppUser;
import com.company.cashwise.infrastructure.UserContextProvider;
import com.company.cashwise.infrastructure.utils.TimeMeasure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.company.cashwise.api.expenses.ExpensesController.EXPENSES_BASE_PATH;

@RestController
@RequestMapping(EXPENSES_BASE_PATH)
class ExpensesController {
    static final Logger log = LoggerFactory.getLogger(ExpensesController.class);
    static final String EXPENSES_BASE_PATH = "/expenses";
    private final ExpensesService expensesService;

    ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @GetMapping("/{rawExpenseId}")
    @TimeMeasure
    ResponseEntity<ExpenseResponseDto> getSingleExpense(
            @PathVariable String rawExpenseId
    ) {
        BudgetAppUser user = UserContextProvider.getUserContext();
        Optional<Expense> expenseById = expensesService.getExpenseById(new ExpenseId(rawExpenseId), user.userId());
        return ResponseEntity.of(expenseById.map(ExpenseResponseDto::fromDomain));
    }

    @GetMapping
    @TimeMeasure
    ResponseEntity<Page<ExpenseResponseDto>> getExpensesByPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "25") Integer size,
            @RequestParam(required = false, defaultValue = "expenseId") String sortBy
    ) {
        BudgetAppUser user = UserContextProvider.getUserContext();
        return ResponseEntity.ok(expensesService.getExpensesPage(
                        PageRequest.of(page, size, Sort.by(sortBy).descending()),
                        user.userId()
                ).map(ExpenseResponseDto::fromDomain)
        );
    }

    @PostMapping
    @TimeMeasure
    ResponseEntity<ExpenseResponseDto> registerNewExpense(
            @RequestBody @Valid RegisterExpenseRequest request
    ) {
        BudgetAppUser user = UserContextProvider.getUserContext();

        return ResponseEntity.ok(
                ExpenseResponseDto.fromDomain(expensesService.registerNewExpense(
                                request.title(), request.amount(), Instant.now(), user.userId(), BudgetId.ofValue(request.budgetId())
                        )
                )
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @TimeMeasure
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
