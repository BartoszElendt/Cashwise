package io.github.szczepanskikrs.budgetoapp.domain.budgettypes;

/**
 * Enum class representing definition of budget types.
 */
public enum BudgetType {
    UNCAPPED("Uncapped budget"),
    CAPPED("Capped, will not allow to add expenses when overfilled"),
    CAPPED_DOUBLE("Capped, will not allow to add expenses when overfilled 2x"),
    CAPPED_TRIPLE("Capped, will not allow to add expenses when overfilled 3x");

    /**
     * Field that is used in enum constructor to cary some description.
     */
    private final String description;

    /**
     * @return string that is {@link BudgetType} enum value description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Enum constructor
     *
     * @param description budget type description
     */
    BudgetType(String description) {
        this.description = description;
    }
}
