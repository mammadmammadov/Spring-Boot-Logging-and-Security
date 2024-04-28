package az.edu.ada.wm2.Assignment2.model.dto;

import az.edu.ada.wm2.Assignment2.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class SignupDto {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    private String email;

    public User toUser(PasswordEncoder encoder) {
        return new User(username, encoder.encode(password), email);
    }

}
