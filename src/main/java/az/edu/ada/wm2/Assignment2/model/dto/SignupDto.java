package az.edu.ada.wm2.Assignment2.model.dto;

import az.edu.ada.wm2.Assignment2.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Data Transfer Object (DTO) class representing user signup data.
 * Contains fields for username, password, and email.
 */
@Data
public class SignupDto {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    private String email;

    /**
     * Converts the SignupDto object to a User entity.
     *
     * @param encoder The PasswordEncoder instance to encode the password.
     * @return The corresponding User entity.
     */
    public User toUser(PasswordEncoder encoder) {
        // Creating a new User entity using the provided username, encoded password, and email
        return new User(username, encoder.encode(password), email);
    }
}
