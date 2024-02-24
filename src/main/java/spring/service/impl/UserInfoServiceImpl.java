package spring.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.entity.User;
import spring.entity.UserInfo;
import spring.repository.UserInfoRepository;
import spring.service.UserInfoService;
import spring.service.UserService;
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public void save(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }

    @Override
    public void updateByUserInfoId(Long userid, UserInfo newUserInfo) {
        userInfoRepository.updateByUserInfoId(userid,newUserInfo);
    }
    @Override
    public UserInfo findById(Long id) {
        return userInfoRepository.findById(id);
    }
    @Override
    public void deleteUserInfo(Long id) {
        userInfoRepository.deleteUserInfo(id);
    }
}
