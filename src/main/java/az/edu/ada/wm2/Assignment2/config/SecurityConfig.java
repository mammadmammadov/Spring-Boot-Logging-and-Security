package az.edu.ada.wm2.Assignment2.config;

import az.edu.ada.wm2.Assignment2.model.entity.User;
import az.edu.ada.wm2.Assignment2.repo.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
public class SecurityConfig {

//    @Value("${spring.h2.console.path}")
//    private String h2Console;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//
//        List<UserDetails> users = new ArrayList<>();
//
//        Collections.addAll(users,
//                new User("admin", passwordEncoder().encode("admin"),
//                        Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")
//                        )),
//                new User("nsadili", passwordEncoder().encode("12345"),
//                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")
//                        ))
//        );
//
//        return new InMemoryUserDetailsManager(users);
//    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repo) {
        return username -> {
            Optional<User> res = repo.findByUsername(username);

            return res.orElseThrow(() ->
                    new UsernameNotFoundException(username + " not found")
            );
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/login/**").permitAll()
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
