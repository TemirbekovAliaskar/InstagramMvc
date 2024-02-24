package spring.repository;

import spring.entity.UserInfo;

public interface UserInfoRepository {

    void save(UserInfo userInfo);
    void updateByUserInfoId(Long userInfoId, UserInfo userInfo);
    UserInfo findById(Long id);
    void deleteUserInfo(Long id);
}
