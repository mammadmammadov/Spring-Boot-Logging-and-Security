package az.edu.ada.wm2.Assignment2.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity class representing a user.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;
    private String roles; // String representing user roles persisted in the database

    @Transient
    private List<String> authorities = new ArrayList<>(Arrays.asList("ROLE_USER")); // List of user roles

    /**
     * Returns the list of user authorities (roles).
     *
     * @return List of GrantedAuthority objects representing user roles.
     */
    @Override
    public List<GrantedAuthority> getAuthorities() {
        return this.authorities.stream().map(
                        role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    /**
     * Constructor for creating a new user with username, password, and email.
     *
     * @param username Username of the user.
     * @param password Encrypted password of the user.
     * @param email    Email address of the user.
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Method to add a role (authority) to the user.
     *
     * @param authority Role to add to the user.
     * @return The User object with the added role.
     */
    public User addRole(String authority) {
        this.authorities.add(authority);
        return this;
    }

    /**
     * Method executed before persisting or updating the user entity to save the roles to the roles attribute.
     */
    @PrePersist
    @PreUpdate
    private void saveRoles() {
        this.roles = String.join(";", this.authorities);
    }

    /**
     * Method executed after loading the user entity to read the roles from the roles attribute.
     */
    @PostLoad
    private void readRoles() {
        this.authorities = Arrays.stream(this.roles.split(";"))
                .collect(Collectors.toList());
    }

    // Methods below are implementations of UserDetails interface methods

    @Override
    public boolean isAccountNonExpired() {
        return true; // User account is never expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // User account is never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // User credentials are never expired
    }

    @Override
    public boolean isEnabled() {
        return true; // User account is always enabled
    }
}
