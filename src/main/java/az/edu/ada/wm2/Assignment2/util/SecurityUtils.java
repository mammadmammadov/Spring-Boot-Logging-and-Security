package az.edu.ada.wm2.Assignment2.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Utility class for security-related operations.
 */
public class SecurityUtils {

    /**
     * Checks if the current authenticated user has admin privileges.
     *
     * @return True if the current user is an admin, false otherwise.
     */
    public static boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
    }
}
