package com.company.cashwise.infrastructure;

import com.company.cashwise.domain.users.BudgetAppUser;
import org.springframework.security.core.context.SecurityContextHolder;

public enum UserContextProvider {
    INSTANCE;

    public static BudgetAppUser getUserContext() {
        return (BudgetAppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
