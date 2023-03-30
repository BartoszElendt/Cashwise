package io.github.szczepanskikrs.budgetoapp.domain.budgettypes;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default BudgetTypeService, implementation can be package-private, it returns all BudgetTypes from enum.
 * {@link Service} marks it as component managed by SpringBoot, so spring will create singleton Bean of this type and inject
 * it where we will need BudgetTypesService instances.
 */
@Service
class DefaultBudgetTypesService implements BudgetTypesService {
    /**
     * Needs to be public due to how interfaces works, so it can be used as instance of {@link BudgetTypesService}
     * @return List of all budget types from enum {@link BudgetType}.
     */
    @Override
    public List<BudgetType> allBudgetTypes() {
        return List.of(BudgetType.values());
    }
}
