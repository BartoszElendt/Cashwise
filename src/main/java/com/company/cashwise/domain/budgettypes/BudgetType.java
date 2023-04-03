package com.company.cashwise.domain.budgettypes;

public enum BudgetType {
    UNCAPPED("Uncapped budget"),
    CAPPED("Capped, will not allow to add expenses when overfilled"),
    CAPPED_DOUBLE("Capped, will not allow to add expenses when overfilled 2x"),
    CAPPED_TRIPLE("Capped, will not allow to add expenses when overfilled 3x");

    private final String description;

    public String getDescription() {
        return description;
    }

    BudgetType(String description) {
        this.description = description;
    }
}
