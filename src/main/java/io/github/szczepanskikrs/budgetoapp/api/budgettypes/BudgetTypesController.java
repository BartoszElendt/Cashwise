package io.github.szczepanskikrs.budgetoapp.api.budgettypes;

import io.github.szczepanskikrs.budgetoapp.domain.budgettypes.BudgetTypesService;
import io.github.szczepanskikrs.budgetoapp.infrastructure.utils.TimeMeasure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller used for getting budget type definitions. It is package-private for the sake of hiding classes that doesn't need to be public.
 */
// Registers as REST Controller so we can handle HTTP requests
@RestController
// Sets base mapping that you will use to call this application using HTTP
// Corresponds to `/budgets/types`
// You will most likely use this using http://localhost:8080/budgets/types
@RequestMapping(BudgetTypesController.BUDGET_TYPES_BASE_MAPPING)
class BudgetTypesController {
    // Budget type service, we use interface here to have nice dependency injection on interfaces. We use BudgetTypeService instead of package-private DefaultBudgetTypeService.
    private final BudgetTypesService service;

    // Default constructor used by Spring for dependency injection.
    BudgetTypesController(BudgetTypesService service) {
        this.service = service;
    }

    //Defines GET endpoint under `/budgets/types`
    @GetMapping
    @TimeMeasure
    // Returns budget types Response entity, that will return List of all BudgetType mapped to BudgetTypeResponse
    ResponseEntity<List<BudgetTypeResponse>> getBudgetType(){
        return ResponseEntity.ok(service.allBudgetTypes().stream().map(BudgetTypeResponse::fromDomain).toList());
    }

    // We don't want to use `magic` strings, so we assign them to constants if we can.
    final static String BUDGET_TYPES_BASE_MAPPING = "/budgets/types";
}
