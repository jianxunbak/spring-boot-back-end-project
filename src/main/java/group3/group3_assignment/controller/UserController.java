package group3.group3_assignment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// import org.springframework.web.bind.annotation.*;

import group3.group3_assignment.entity.User;
import group3.group3_assignment.entity.UserValidationGroup;
import group3.group3_assignment.service.UserService;
import group3.group3_assignment.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a user
    @PostMapping("")
    public ResponseEntity<User> addUser(@Validated(UserValidationGroup.CreateUserGroup.class) @RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Read - Get all Users
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Read - Get one User
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUser(id); // Updated to directly pass Integer
        return ResponseEntity.ok(user);
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
            @Validated(UserValidationGroup.EditUserGroup.class) @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    // Update User password
    @PutMapping("/{id}/password")
    public ResponseEntity<String> updateUserPassword(@PathVariable Long id,
            @Validated(UserValidationGroup.EditPasswordGroup.class) @RequestBody User user) {
        logger.info("User update request received: {}", user);

        userService.updatePassword(id, user);
        return new ResponseEntity<>("Successfully edited password", HttpStatus.OK);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User with id " + id + " is deleted", HttpStatus.NO_CONTENT);
    }

}