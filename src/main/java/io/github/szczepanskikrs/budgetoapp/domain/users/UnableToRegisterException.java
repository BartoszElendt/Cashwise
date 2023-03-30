package io.github.szczepanskikrs.budgetoapp.domain.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Domain exception we throw when user with given username or password is already registered
 */
@ResponseStatus(HttpStatus.CONFLICT)
class UnableToRegisterException extends IllegalStateException {
}
