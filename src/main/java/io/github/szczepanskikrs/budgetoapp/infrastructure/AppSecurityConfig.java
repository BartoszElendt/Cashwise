package io.github.szczepanskikrs.budgetoapp.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Class used for security configuration
 */
@Configuration
@EnableWebSecurity
class AppSecurityConfig {

    /**
     * Never store passwords as plain string, always use some form of PasswordEncoder here use {@link BCryptPasswordEncoder}
     * @return BCryptPasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager used by spring security. Based on PasswordEncoder, UserDetailsService and HttpSecurity it will be used to authenticate incoming requests.
     * If given request in under path that is secured it will try to get {@link UserDetails} from {@link UserDetailsService}
     * If given user is found it will get password, and compare it with credential from incoming request encoded by PasswordEncoder.
     * If passwords match, roles match, user is active and not expired/suspended it will store UserDetails that you can access from SecurityContextHolder
     * @param http pretty much security config we set up in filterChain method.
     * @param userDetailsService either mongo or in-memory userDetails service we have in our code check {@link DefaultUserDetailsService}.
     * When profile is {MONGO_SECURITY_SOURCE} we use {@link MongoUserRepository} as source of UserDetails
     * If we use {IN_MEMORY_SECURITY_SOURCE}  we use {@link InMemoryUserRepository} as source of UserDetails
     * @param encoder passwordEncoder that we declared as bean in passwordEncoder method in line 28
     * @return
     * @throws Exception
     */
    @Bean
    AuthenticationManager customAuthenticationManager(
            HttpSecurity http,
            UserDetailsService userDetailsService,
            PasswordEncoder encoder
    ) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    /**
     * Security config, we use basicSecurity with cors disabled and csrf disabled.
     * We permit all to /users so new users can register.
     * And only registered users with correct Authorization headers can access any other endpoint.
     * More on CSRF https://sekurak.pl/czym-jest-podatnosc-csrf-cross-site-request-forgery/
     * More on CORS https://www.youtube.com/watch?v=4KHiSt0oLJ0
     * @param security configurer used to configure security.
     * @return configured security filter chain.
     * @throws Exception
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security
                .cors()
                .disable()
                .csrf()
                .disable()
                .authorizeRequests(
                        // Permit all traffic for /users
                        securityCustomizer -> securityCustomizer.antMatchers("/users").permitAll()
                )
                .authorizeRequests(
                        // Require authentication for any other endpoint
                        securityCustomizer -> securityCustomizer.antMatchers("/**").authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
