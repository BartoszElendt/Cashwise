package io.github.szczepanskikrs.budgetoapp.api.budgets;

import io.github.szczepanskikrs.budgetoapp.domain.budgets.Budget;
import io.github.szczepanskikrs.budgetoapp.domain.budgets.BudgetId;
import io.github.szczepanskikrs.budgetoapp.domain.budgets.BudgetService;
import io.github.szczepanskikrs.budgetoapp.domain.users.BudgetAppUser;
import io.github.szczepanskikrs.budgetoapp.infrastructure.UserContextProvider;
import io.github.szczepanskikrs.budgetoapp.infrastructure.utils.TimeMeasure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import static io.github.szczepanskikrs.budgetoapp.api.budgets.BudgetsController.BUDGETS_BASE_MAPPING;

/**
 * Controller used for CRUD operations on budgets. It is package-private for the sake of hiding classes that doesn't need to be public.
 */
@RestController
// Registers as REST Controller, so we can handle HTTP requests
// Corresponds to `/budgets` and `/budgets/{id}` where `{id}` is placeholder for BudgetId
// You will most likely use this using http://localhost:8080/budgets
@RequestMapping(BUDGETS_BASE_MAPPING)
class BudgetsController {
    /**
     * Budget service, injected by Spring using constructor injection. Real implementation injected here is {@link DefaultBudgetService}
     * but we use {@link BudgetService} interface here.
     */
    private final BudgetService budgetService;

    /**
     * Main constructor
     *
     * @param budgetService service injected by spring
     */
    BudgetsController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    /**
     * Method that is responsible for registering new budget
     * You will most likely use this sending POST request to /budgets
     *
     * @param request {@link RegisterBudgetRequest} request that is used to register budget.
     * @return Response {@link BudgetDto} wrapped with {@link ResponseEntity}
     * with status code 201 CREATED with {@link org.springframework.http.HttpHeaders} LOCATION
     * header that has corresponding budgetId as part of URI {@link BudgetId}
     */
    @PostMapping
    @TimeMeasure
    ResponseEntity<BudgetDto> registerNewBudget(
            // Indicates that this endpoint requires @RequestBody if type RegisterBudgetRequest
            @RequestBody RegisterBudgetRequest request
    ) {
        //TO BE REPLACED WITH SECURITY
        BudgetAppUser user = UserContextProvider.getUserContext();
        //Registers new budget
        Budget budget = budgetService.registerNewBudget(request.name(), user.userId(), request.cap(), request.type());
        // Wraps registered budget in ResponseEntity with status code 201 CREATED
        return ResponseEntity.created(
                        //Defines Location header value
                        URI.create("/budgets/" + budget.budgetId().value())
                )
                .body(
                        // Defines ResponseEntity body
                        BudgetDto.fromDomain(budget)
                );
    }

    /**
     * Returns {@link Page} of {@link BudgetDto} objects that will be returned by /budgets. We use paging here.
     * Read more about paging <a href="https://www.baeldung.com/rest-api-pagination-in-spring">Paging description Baeldung</a>
     *
     * @param page int representing requested page
     * @param size int representing requested page size
     * @return Page object with requested page of {@link BudgetDto}
     */
    @GetMapping
    @TimeMeasure
    ResponseEntity<Page<BudgetDto>> getBudgetsByPage(
            // Request param
            @RequestParam Integer page,
            // Request param
            @RequestParam Integer size
    ) {
        // TODO replace with security
        BudgetAppUser user = UserContextProvider.getUserContext();
        // Wraps requested budgets in ResponseEntity<Page<BudgetDto> with status code OK even when empty.
        // When empty, page will have no elements, but we still return OK
        return ResponseEntity.ok(budgetService.getBudgets(PageRequest.of(page, size), user.userId()).map(BudgetDto::fromDomain));
    }

    /**
     * Gets single budget from API
     *
     * @param budgetId {@link BudgetId} of requested budget
     * @return ResponseEntity<BudgetDto> if budget was found we return 200 OK if budget was not found we return empty {@link Optional}
     * When empty Optional is returned by BudgetService ResponseEntity will return 404 Not Found
     */
    @GetMapping("/{budgetId}")
    @TimeMeasure
    ResponseEntity<BudgetDto> getSingleBudget(
            // Path variable that defines budget that will be affected
            @PathVariable String budgetId) {
        BudgetAppUser user = UserContextProvider.getUserContext();
        // We use ResponseEntity.of() and it handles 200 or 404 depending on Optional having value or being empty.
        return ResponseEntity.of(budgetService.getBudget(BudgetId.ofValue(budgetId), user.userId()).map(BudgetDto::fromDomain));
    }

    @GetMapping("/{budgetId}/status")
    @TimeMeasure
    ResponseEntity<BudgetStateDto> getSingleBudgetStatus(
            // Path variable that defines budget that will be affected
            @PathVariable String budgetId) {
        BudgetAppUser user = UserContextProvider.getUserContext();
        // We use ResponseEntity.of() and it handles 200 or 404 depending on Optional having value or being empty.
        return ResponseEntity.of(budgetService.getBudgetState(BudgetId.ofValue(budgetId), user.userId())
                .map(BudgetStateDto::fromDomain)
        );
    }

    /**
     * Deletes single budget from API
     *
     * @param budgetId {@link BudgetId} of deleted budget
     * @return ResponseEntity<BudgetDto> We always return 204 no content, even when budget is not found.
     */
    @DeleteMapping("/{budgetId}")
    @TimeMeasure
    ResponseEntity<BudgetDto> deleteBudget(@PathVariable String budgetId) {
        BudgetAppUser user = UserContextProvider.getUserContext();
        budgetService.deleteBudget(BudgetId.ofValue(budgetId), user.userId());
        // Returns no content to be idempotent
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates single budget
     *
     * @param budgetId {@link BudgetId} of deleted budget
     * @param request  {@link RegisterBudgetRequest} body that will be used to update budget.
     * @return ResponseEntity<BudgetDto> We update or create new budget.
     */
    @PutMapping("/{budgetId}")
    @TimeMeasure
    ResponseEntity<BudgetDto> updateBudget(@PathVariable String budgetId, @RequestParam RegisterBudgetRequest request) {
        BudgetAppUser user = UserContextProvider.getUserContext();
        return ResponseEntity.of(
                budgetService.updateBudget(BudgetId.ofValue(budgetId), request.name(), user.userId(), request.cap(), request.type())
                        .map(BudgetDto::fromDomain)
        );
    }

    /**
     * Base mapping constant all methods defined within this controller will have mappings that starts with /budgets thanks to
     * `@RequestMapping(BUDGETS_BASE_MAPPING)` annotation
     */
    static final String BUDGETS_BASE_MAPPING = "/budgets";
}
