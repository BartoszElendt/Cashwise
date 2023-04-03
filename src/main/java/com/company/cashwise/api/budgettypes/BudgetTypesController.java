package com.company.cashwise.api.budgettypes;

import com.company.cashwise.domain.budgettypes.BudgetTypesService;
import com.company.cashwise.infrastructure.utils.TimeMeasure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(BudgetTypesController.BUDGET_TYPES_BASE_MAPPING)
class BudgetTypesController {
    final static String BUDGET_TYPES_BASE_MAPPING = "/budgets/types";
    private final BudgetTypesService service;

    BudgetTypesController(BudgetTypesService service) {
        this.service = service;
    }

    @GetMapping
    @TimeMeasure
    ResponseEntity<List<BudgetTypeResponse>> getBudgetType() {
        return ResponseEntity.ok(service.allBudgetTypes().stream().map(BudgetTypeResponse::fromDomain).toList());
    }
}
