package az.edu.ada.wm2.Assignment2.service;

import az.edu.ada.wm2.Assignment2.model.dto.SignupDto;
import az.edu.ada.wm2.Assignment2.model.entity.User;
import az.edu.ada.wm2.Assignment2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void signup(SignupDto signupDto) {
        User user = signupDto.toUser(passwordEncoder);
        userRepository.save(user);
    }
}
