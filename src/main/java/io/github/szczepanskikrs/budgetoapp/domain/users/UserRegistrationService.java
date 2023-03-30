package io.github.szczepanskikrs.budgetoapp.domain.users;

import java.util.List;

/**
 * Interface used to register new users
 */
public interface UserRegistrationService {
    //Default roles (we add them to spring security, only as example as UserDetails need them)
    List<String> defaultRoles = List.of("USER");
    //Method that we use to register new users
    BudgetAppUser registerNewUser(String userName, String rawPassword, String email, List<String> roles);
}
