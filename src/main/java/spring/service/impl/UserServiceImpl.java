package spring.service.impl;

import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.entity.Follower;
import spring.entity.User;
import spring.entity.UserInfo;
import spring.repository.UserRepository;
import spring.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public void register(User user) {
        Follower follower = new Follower();
        UserInfo userInfo = new UserInfo();
        user.setUserInfo(userInfo);
        user.setFollower(follower);
        userInfo.setUser(user);
        follower.setUser(user);
        userRepository.register(user);
    }
    @Override
    public User login(User user) {
        return userRepository.login(user);
    }

    @Override
    public User finById(Long id) {
      return   userRepository.findById(id);
    }

    @Override
    public List<User> usersAll() {
        return userRepository.usersAll();
    }
    @Override
    public User searchUserName(String userName) {
        return userRepository.searchUserName(userName);
    }
}
