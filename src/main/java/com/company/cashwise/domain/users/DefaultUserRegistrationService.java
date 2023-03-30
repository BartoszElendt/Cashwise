package com.company.cashwise.domain.users;

import com.company.cashwise.infrastructure.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

/**
 * Service used to register new users
 * {@link Service} tells spring that it should be registered in context
 */
@Service
class DefaultUserRegistrationService implements UserRegistrationService {
    // User repository, depending on profile can be in-memory or mongo
    private final UserRepository repository;
    // Supplier used to get userIds
    private final Supplier<UserId> userIdSupplier;
    // PasswordEncoder user to encode passwords. In this application we use BCryptPasswordEncoder.
    private final PasswordEncoder encoder;

    // Constructor with all parameters
    public DefaultUserRegistrationService(UserRepository repository, Supplier<UserId> userIdSupplier, PasswordEncoder encoder) {
        this.repository = repository;
        this.userIdSupplier = userIdSupplier;
        this.encoder = encoder;
    }


    @Override
    // Implementation of method that we use to register new users
    public BudgetAppUser registerNewUser(String userName, String rawPassword, String email, List<String> roles) {
        // We check if user with email of username exists
        if (repository.existsByEmailOrName(email, userName)) {
            // If we have user registered with given userName or email we throw exception and return 409
            throw new UnableToRegisterException();
        }

        // We create new user with new userId from supplier and encode password using encoder.encode(rawPassword)
        BudgetAppUser budgetAppUser = new BudgetAppUser(userIdSupplier.get(), userName, email, encoder.encode(rawPassword), roles, true);

        // We save user with encrypted password in repository (either in-memory or mongo)
        return repository.save(budgetAppUser);
    }
}
