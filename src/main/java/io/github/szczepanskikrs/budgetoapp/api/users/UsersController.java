package io.github.szczepanskikrs.budgetoapp.api.users;

import io.github.szczepanskikrs.budgetoapp.domain.users.BudgetAppUser;
import io.github.szczepanskikrs.budgetoapp.domain.users.UserRegistrationService;
import io.github.szczepanskikrs.budgetoapp.infrastructure.utils.TimeMeasure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Class that we use for registering users
 */
// RestController annotation tells spring that it should use @Controller(pretty much alias for @Component) & @ResponseBody meta-information.
@RestController
// Exposes endpoint under /users for user registration
@RequestMapping("/users")
// Class doesn't need to be public it shouldn't leak out of `api` package.
class UsersController {
    // Service used for registration
    private final UserRegistrationService registrationService;

    // Constructor
    UsersController(UserRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Endpoint used to register new users.
     * @param request Body of request see {@link UserRegistrationRequest}
     * @return response entity with user details (skipping password)
     */
    @PostMapping
    @TimeMeasure
    ResponseEntity<UserDetailsResponse> registerUser(
            @Valid @RequestBody UserRegistrationRequest request
    ) {
        BudgetAppUser budgetAppUser = registrationService.registerNewUser(request.name(), request.password(), request.email(), UserRegistrationService.defaultRoles);
        return ResponseEntity.ok(UserDetailsResponse.fromDomain(budgetAppUser));
    }
}
