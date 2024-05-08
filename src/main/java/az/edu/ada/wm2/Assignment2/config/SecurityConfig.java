package az.edu.ada.wm2.Assignment2.config;

import az.edu.ada.wm2.Assignment2.model.entity.User;
import az.edu.ada.wm2.Assignment2.repo.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
public class SecurityConfig {

    /**
     * This method defines a PasswordEncoder bean for encrypting passwords.
     *
     * @return A PasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * This method defines a UserDetailsService bean for fetching user details from the database.
     *
     * @param repo The UserRepository to fetch user details.
     * @return A UserDetailsService instance.
     */
    @Bean
    public UserDetailsService userDetailsService(UserRepository repo) {
        return username -> {
            Optional<User> res = repo.findByUsername(username);

            return res.orElseThrow(() ->
                    new UsernameNotFoundException(username + " not found")
            );
        };
    }

    /**
     * This method configures the security filter chain for handling HTTP requests.
     *
     * @param http The HttpSecurity instance to configure security settings.
     * @return A SecurityFilterChain instance.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // disabling CSRF protection
                .headers(AbstractHttpConfigurer::disable) // disabling header protections
                .authorizeHttpRequests(request -> {
                    // defining authorization rules for different URL paths
                    request.requestMatchers("/users/**").hasAnyRole("ADMIN", "USER");
                    request.requestMatchers("/admins/**").hasRole("ADMIN");
                    request.requestMatchers("/", "/signup/**").permitAll();
                    request.requestMatchers("/books/**").authenticated();
                    request.requestMatchers(PathRequest.toH2Console()).permitAll(); // allowing access to H2 console
                    request.anyRequest().authenticated(); // authenticating any other request
                })
                .formLogin(form -> form
                        .loginPage("/login") // specifying the custom login page
                        .defaultSuccessUrl("/books", true) // redirecting to "/books" after successful login
                        .permitAll()); // allowing access to the login page without authentication
        return http.build();
    }

}

