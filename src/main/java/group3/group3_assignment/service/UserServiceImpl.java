package group3.group3_assignment.service;

import group3.group3_assignment.controller.RecipeController;
import group3.group3_assignment.entity.User;
import group3.group3_assignment.exception.UserNotAuthorizeException;
import group3.group3_assignment.exception.UserNotFoundException;
import group3.group3_assignment.repository.UserRepo;
import group3.group3_assignment.repository.RecipeRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RecipeRepo recipeRepo;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepo userRepo, RecipeRepo recipeRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.recipeRepo = recipeRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addUser(User user) {
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            // Encrypt the password before saving the user
            user.setPassword(encryptedPassword);

        }
        return userRepo.save(user);
    }

    @Override
    public User getUser(Long id) {
        User selectedUser = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id: " + id + "is not found."));
        return selectedUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthorizeException(id, "edit", "another user details");
        }
        String authenticatedUserId = (String) authentication.getDetails();

        if (!authenticatedUserId.equals(id.toString())) {
            throw new UserNotAuthorizeException(id, "edit", "another user details");
        }

        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id: " + id + "is not found."));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setAboutMe(user.getAboutMe());
        existingUser.setProfilePicture(user.getProfilePicture());
        return userRepo.save(existingUser);
    }

    @Override
    public void updatePassword(Long id, User user) {
        logger.info("User update request received: {}", user);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthorizeException(id, "edit", "another user details");
        }
        String authenticatedUserId = (String) authentication.getDetails();

        if (!authenticatedUserId.equals(id.toString())) {
            throw new UserNotAuthorizeException(id, "edit", "another user details");
        }

        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id: " + id + "is not found."));

        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(existingUser);

    }

    @Override
    public void deleteUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();

        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id: " + id + "is not found."));

        if (authenticatedUsername.equals(existingUser.getUsername())) {
            userRepo.delete(existingUser);
        } else
            throw new UserNotAuthorizeException(id, "delete", "another user");
    }
}
