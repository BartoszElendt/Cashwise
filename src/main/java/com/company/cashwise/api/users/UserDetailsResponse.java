package com.company.cashwise.api.users;

import com.company.cashwise.domain.users.BudgetAppUser;

record UserDetailsResponse(
        String username,
        String email,
        String userId
) {
    static UserDetailsResponse fromDomain(BudgetAppUser user) {
        return new UserDetailsResponse(user.getUsername(), user.email(), user.userId().value());
    }
}
