package io.github.szczepanskikrs.budgetoapp.infrastructure;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * User details service used by Spring Security to load information about users when someone calls our secured API
 */
@Service
class DefaultUserDetailsService implements UserDetailsService {
    /**
     * Repository we will inject MongoUserRepository on InMemoryUserRepository depending on active Profile
     */
    private final UserRepository repository;

    public DefaultUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}