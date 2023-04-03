package com.company.cashwise.api.budgets;

import com.company.cashwise.domain.budgets.Budget;
import com.company.cashwise.domain.budgets.BudgetId;
import com.company.cashwise.domain.budgets.BudgetService;
import com.company.cashwise.domain.users.BudgetAppUser;
import com.company.cashwise.infrastructure.UserContextProvider;
import com.company.cashwise.infrastructure.utils.TimeMeasure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import static com.company.cashwise.api.budgets.BudgetsController.BUDGETS_BASE_MAPPING;


@RestController

@RequestMapping(BUDGETS_BASE_MAPPING)
class BudgetsController {
    static final String BUDGETS_BASE_MAPPING = "/budgets";
    private final BudgetService budgetService;

    BudgetsController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    @TimeMeasure
    ResponseEntity<BudgetDto> registerNewBudget(
            @RequestBody RegisterBudgetRequest request
    ) {
        BudgetAppUser user = UserContextProvider.getUserContext();
        Budget budget = budgetService.registerNewBudget(request.name(), user.userId(), request.cap(), request.type());
        return ResponseEntity.created(
                        URI.create("/budgets/" + budget.budgetId().value())
                )
                .body(
                        BudgetDto.fromDomain(budget)
                );
    }

    @GetMapping
    @TimeMeasure
    ResponseEntity<Page<BudgetDto>> getBudgetsByPage(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        BudgetAppUser user = UserContextProvider.getUserContext();
        return ResponseEntity.ok(budgetService.getBudgets(PageRequest.of(page, size), user.userId()).map(BudgetDto::fromDomain));
    }

    @GetMapping("/{budgetId}")
    @TimeMeasure
    ResponseEntity<BudgetDto> getSingleBudget(
            @PathVariable String budgetId) {
        BudgetAppUser user = UserContextProvider.getUserContext();
        return ResponseEntity.of(budgetService.getBudget(BudgetId.ofValue(budgetId), user.userId()).map(BudgetDto::fromDomain));
    }

    @GetMapping("/{budgetId}/status")
    @TimeMeasure
    ResponseEntity<BudgetStateDto> getSingleBudgetStatus(
            @PathVariable String budgetId) {
        BudgetAppUser user = UserContextProvider.getUserContext();
        return ResponseEntity.of(budgetService.getBudgetState(BudgetId.ofValue(budgetId), user.userId())
                .map(BudgetStateDto::fromDomain)
        );
    }

    @DeleteMapping("/{budgetId}")
    @TimeMeasure
    ResponseEntity<BudgetDto> deleteBudget(@PathVariable String budgetId) {
        BudgetAppUser user = UserContextProvider.getUserContext();
        budgetService.deleteBudget(BudgetId.ofValue(budgetId), user.userId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{budgetId}")
    @TimeMeasure
    ResponseEntity<BudgetDto> updateBudget(@PathVariable String budgetId, @RequestParam RegisterBudgetRequest request) {
        BudgetAppUser user = UserContextProvider.getUserContext();
        return ResponseEntity.of(
                budgetService.updateBudget(BudgetId.ofValue(budgetId), request.name(), user.userId(), request.cap(), request.type())
                        .map(BudgetDto::fromDomain)
        );
    }
}
