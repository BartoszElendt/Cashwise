package io.github.szczepanskikrs.budgetoapp.domain.budgettypes;

import java.util.List;

/**
 * Interface that is used to get all budget types from BudgetType enum. It needs to be public,
 * so we can inject instance of DefaultBudgetTypeService using interface without making DefaultBudgetTypeService public.
 */
public interface BudgetTypesService {
    List<BudgetType> allBudgetTypes();
}
