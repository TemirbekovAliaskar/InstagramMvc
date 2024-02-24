package spring.repository;

import org.springframework.stereotype.Repository;
import spring.entity.User;

import java.util.List;

public interface UserRepository {

    void register(User user);
    User login (User user);
    User findById(Long id);
    List<User> usersAll();
    User searchUserName(String userName);

}
