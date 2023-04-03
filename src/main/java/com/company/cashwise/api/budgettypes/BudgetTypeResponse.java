package com.company.cashwise.api.budgettypes;

import com.company.cashwise.domain.budgettypes.BudgetType;

record BudgetTypeResponse(String name, String description) {
    static BudgetTypeResponse fromDomain(BudgetType type) {
        return new BudgetTypeResponse(type.name(), type.getDescription());
    }
}
