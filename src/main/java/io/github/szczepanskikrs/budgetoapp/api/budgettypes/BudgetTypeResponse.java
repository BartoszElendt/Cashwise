package io.github.szczepanskikrs.budgetoapp.api.budgettypes;

import io.github.szczepanskikrs.budgetoapp.domain.budgettypes.BudgetType;

/**
 * Class that is representation of BudgetType that will is send to application clients using REST API /budgets/types
 * Class don't need to be public since we have controller in same package.
 * @param name budget name
 * @param description budget description
 */
record BudgetTypeResponse(String name, String description) {
    // Static method that is used to convert BudgetType to BudgetTypeResponse we return DTO instead of domain objects.
    // This is static method, so can be called without object of type BudgetTypeResponse
    static BudgetTypeResponse fromDomain(BudgetType type) {
        return new BudgetTypeResponse(type.name(), type.getDescription());
    }
}
