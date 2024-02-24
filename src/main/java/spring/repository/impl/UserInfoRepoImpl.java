package spring.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.entity.User;
import spring.entity.UserInfo;
import spring.repository.UserInfoRepository;

@Repository
@RequiredArgsConstructor
//@Transactional
public class UserInfoRepoImpl implements UserInfoRepository {

    private final EntityManager entityManager;


    @Override
    public void save(UserInfo userInfo) {
//        entityManager.getTransaction().begin();
        User user = new User();
        user.setUserInfo(userInfo);
        userInfo.setUser(user);
        entityManager.merge(userInfo);
//        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void updateByUserInfoId(Long userInfoId, UserInfo newUserInfo) {
//        entityManager.getTransaction().begin();
        UserInfo userInfo = entityManager.find(UserInfo.class, userInfoId);
        userInfo.setFullName(newUserInfo.getFullName());
        userInfo.setImage(newUserInfo.getImage());
        userInfo.setGender(newUserInfo.getGender());
        userInfo.setBiography(newUserInfo.getBiography());
        entityManager.merge(userInfo);
        entityManager.persist(userInfo);
//        entityManager.getTransaction().commit();
    }
    @Override
    @Transactional
    public UserInfo findById(Long id) {

        return entityManager.find(UserInfo.class,id);
    }
       
    @Override
    @Transactional
    public void deleteUserInfo(Long id) {
        entityManager.remove(entityManager.find(UserInfo.class,id));
    }
}
