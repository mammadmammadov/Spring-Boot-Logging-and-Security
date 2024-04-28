package az.edu.ada.wm2.Assignment2.config;

import az.edu.ada.wm2.Assignment2.model.entity.User;
import az.edu.ada.wm2.Assignment2.repo.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
        http
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/users/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/admins/**").hasRole("ADMIN")
                .requestMatchers("/", "/signup/**").permitAll()
                .requestMatchers("/books/**").authenticated()
                .requestMatchers(PathRequest.toH2Console()).permitAll() //TBD
//                .requestMatchers(h2Console + "/**").permitAll() //TBD
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/books", true)
                        .permitAll())
        ;


        return http.build();
    }

}
