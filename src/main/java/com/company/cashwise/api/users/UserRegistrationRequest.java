package com.company.cashwise.api.users;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

record UserRegistrationRequest(
        @NotNull
        @NotBlank
        String name,

        @NotNull
        @NotBlank
        @Length(min = 12, max = 64)
        String password,

        @Email
        String email
) {
}
