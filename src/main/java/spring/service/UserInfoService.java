package spring.service;

import spring.entity.UserInfo;

public interface UserInfoService {

    void save(UserInfo userInfo);
    void updateByUserInfoId(Long userid, UserInfo userInfo);
    UserInfo findById(Long id);
    void deleteUserInfo(Long id);
}
