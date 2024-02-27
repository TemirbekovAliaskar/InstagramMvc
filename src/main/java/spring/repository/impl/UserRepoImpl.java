package spring.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import spring.entity.User;
import spring.repository.UserRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepoImpl implements UserRepository {
    private final EntityManager entityManager;
    @Override
    public void register(User user) {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("select u from User  u", User.class).getResultList();
        try {
            for (User currentUser : users) {
                if (!currentUser.getUserName().equalsIgnoreCase(user.getUserName()) && !currentUser.getEmail().equalsIgnoreCase(user.getEmail())){
                    entityManager.persist(user);
                    entityManager.getTransaction().commit();
                }else {
                    System.out.println("Duplicate!");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User login(User user) {
        List<User> users = entityManager.createQuery("select u from  User u", User.class).getResultList();
        try {
            for (User currentUser : users) {
                if (currentUser.getEmail().equalsIgnoreCase(user.getEmail()) && currentUser.getPassword().equalsIgnoreCase(user.getPassword())) {
                    return currentUser;
                }
            }
        } catch (Exception e){
            System.out.println("Incorrect Password or Email");
        }return null;
    }

    @Override
    public User findById(Long userId) {
        try {
            User currentUser = entityManager.createQuery("select u from User u where u.id = :userId", User.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
            if (currentUser != null) {
                return currentUser;
            } else {
                throw new Exception("Error!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }return null;
    }

    @Override
    public List<User> usersAll() {
       return entityManager.createQuery("select u from User u ", User.class).getResultList();
    }

    @Override
    public User searchUserName(String userName) {
        User user = null;
        try {
            user = entityManager.createQuery("select u from User u " +
                            "            where u.userName ilike (:search)" +
                            "            or u.email ilike (:search)", User.class)
                    .setParameter("search", "%"+userName+"%")
                    .getSingleResult();
                return user;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
