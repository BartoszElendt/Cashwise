package io.github.szczepanskikrs.budgetoapp.api.users;

import io.github.szczepanskikrs.budgetoapp.domain.users.BudgetAppUser;

/**
 * Record that represents user registration response. Remember Records includes hashCode, equals, toString etc.
 *
 * @param username
 * @param email
 * @param userId
 */
record UserDetailsResponse(
        String username,
        String email,
        String userId
) {
    static UserDetailsResponse fromDomain(BudgetAppUser user) {
        return new UserDetailsResponse(user.getUsername(), user.email(), user.userId().value());
    }
}
