package group3.group3_assignment.service;

import group3.group3_assignment.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User getUser(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    void updatePassword(Long id, User user);

    void deleteUser(Long id);

}