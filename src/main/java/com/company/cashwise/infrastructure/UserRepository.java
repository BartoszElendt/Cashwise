package com.company.cashwise.infrastructure;

import com.company.cashwise.domain.users.BudgetAppUser;

import java.util.Optional;
/**
 * UserRepository
 * Depending on used profile we have 2 implementations {@link InMemoryUserRepository} and {@link MongoUserRepository}.
 */
public interface UserRepository {
    Optional<BudgetAppUser>  findByName(String name);
    BudgetAppUser save(BudgetAppUser budgetAppUser);
    boolean existsByEmailOrName(String email, String name);
}
