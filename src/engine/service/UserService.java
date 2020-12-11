package engine.service;

import engine.model.User;

import java.util.List;

public interface UserService {
    User saveNewUser(User user);
    User find(int id);
    List<User> findAll();
}
