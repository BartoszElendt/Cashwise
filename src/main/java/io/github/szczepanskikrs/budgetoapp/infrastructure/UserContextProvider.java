package io.github.szczepanskikrs.budgetoapp.infrastructure;

import io.github.szczepanskikrs.budgetoapp.domain.users.BudgetAppUser;
import org.springframework.security.core.context.SecurityContextHolder;

public enum UserContextProvider {
    INSTANCE;

    public static BudgetAppUser getUserContext() {
        return (BudgetAppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
