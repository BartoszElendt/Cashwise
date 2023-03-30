package com.company.cashwise.infrastructure;

import com.company.cashwise.domain.users.BudgetAppUser;
import com.company.cashwise.AppProfiles;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * In memory implementation of UserRepository
 * Used only when IN_MEMORY_SECURITY_SOURCE profile is active.
 * Stores and retrieves user information from hashmap
 */
@Component
@Profile({AppProfiles.IN_MEMORY_SECURITY_SOURCE, AppProfiles.LOCAL})
class InMemoryUserRepository implements UserRepository {
    private final Map<String, BudgetAppUser> storage = new HashMap<>();

    @Override
    public Optional<BudgetAppUser> findByName(String name) {
        return Optional.ofNullable(storage.get(name));
    }

    @Override
    public BudgetAppUser save(BudgetAppUser budgetAppUser) {
        storage.put(budgetAppUser.getUsername(), budgetAppUser);
        return budgetAppUser;
    }

    @Override
    public boolean existsByEmailOrName(String email, String name) {
        return storage.entrySet()
                .stream()
                .anyMatch(userAlreadyExistsPredicate(email, name));
    }

    private static Predicate<Map.Entry<String, BudgetAppUser>> userAlreadyExistsPredicate(String email, String name) {
        return entry -> Objects.equals(entry.getValue().email(), email) || Objects.equals(entry.getValue().name(), name);
    }
}
