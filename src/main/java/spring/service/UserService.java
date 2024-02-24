package spring.service;

import spring.entity.User;

import java.util.List;

public interface UserService {
    void register(User user);
    User login(User user);
    User finById(Long id);
    List<User> usersAll();

    User searchUserName(String userName);
}
