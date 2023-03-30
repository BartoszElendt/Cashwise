package com.company.cashwise.domain.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Record that is used as representation of application  user
 * It implements {@link UserDetails} so we can use it in SpringSecurity
 * We store password as string encoded using BCrypt.
 * For additional security some salt could be added.
 * {@link UserDetails} requires methods like isAccountNonExpired to properly handle authentication
 * In short all this boolean methods needs to return true in order for SpringSecurity to authorize request.
 *
 * @param userId
 * @param name
 * @param email
 * @param password
 * @param roles
 * @param enabled
 */
@Document(collection = "users")
public record BudgetAppUser(
        @Id
        UserId userId,
        String name,
        String email,
        String password,
        List<String> roles,
        Boolean enabled
) implements UserDetails {
    /**
     * Represents an authority granted to an {@link org.springframework.security.core.Authentication} object.
     * A GrantedAuthority must either represent itself as a String or be specifically supported by an {@link org.springframework.security.access.AccessDecisionManager}.
     * We pretty much extract roles from this
     **/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(SimpleGrantedAuthority::new).toList();
    }

    // Needed by UserDetails
    @Override
    public String getPassword() {
        return password;
    }

    // Needed by UserDetails
    @Override
    public String getUsername() {
        return name;
    }

    // Needed by UserDetails
    @Override
    public boolean isAccountNonExpired() {
        // Needs to be true, or you will get unauthorized from SpringSecurity
        return true;
    }

    // Needed by UserDetails
    @Override
    public boolean isAccountNonLocked() {
        // Needs to be true, or you will get unauthorized from SpringSecurity
        return true;
    }

    // Needed by UserDetails
    @Override
    public boolean isCredentialsNonExpired() {
        // Needs to be true, or you will get unauthorized from SpringSecurity
        return true;
    }

    // Needed by UserDetails
    @Override
    public boolean isEnabled() {
        // Needs to be true, or you will get unauthorized from SpringSecurity
        return enabled;
    }
}