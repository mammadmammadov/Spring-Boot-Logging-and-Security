package az.edu.ada.wm2.Assignment2.init;

import az.edu.ada.wm2.Assignment2.model.entity.User;
import az.edu.ada.wm2.Assignment2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *  Class responsible for initializing database with default users.
 */
@Component
public class DbUsersBootstrapper {

    /**
     * This method initializes the database with default users using an ApplicationRunner.
     *
     * @param userRepo The UserRepository instance to interact with the user database.
     * @param encoder  The PasswordEncoder instance to encode user passwords.
     * @return An ApplicationRunner implementation that populates the database with default users.
     */
    @Bean
    public ApplicationRunner init(UserRepository userRepo, PasswordEncoder encoder) {
        return (args) -> {
            // Creating an admin user with username "admin", password "admin", and email "admin_user@ada.edu.az"
            User adminUser = new User("admin",
                    encoder.encode("admin"), "admin_user@ada.edu.az");
            // Adding the "ROLE_ADMIN" role to the admin user
            userRepo.save(adminUser.addRole("ROLE_ADMIN"));

            // Creating a regular user with username "mammad", password "12345", and email "mammad@ada.edu.az"
            userRepo.save(
                    new User("mammad",
                            encoder.encode("12345"), "mammad@ada.edu.az"));
        };
    }

}
