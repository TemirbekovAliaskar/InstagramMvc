package spring.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.entity.Follower;
import spring.entity.User;
import spring.repository.FollowerRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class FollowerRepoImpl implements FollowerRepository{

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public int subscribers(Long followerId) {
        Follower follower = entityManager.find(Follower.class, followerId);
        return follower.getSubscribers().size();
    }

    @Override
    public int subscriptions(Long followerId) {
        Follower follower = entityManager.find(Follower.class, followerId);
        return follower.getSubscriptions().size();
    }

    public void following(Long currentUserId, Long foundUserId) {


        User currentUser = entityManager.find(User.class, currentUserId);
        User foundUser = entityManager.find(User.class, foundUserId);

        List<Long> subscriptions = currentUser.getFollower().getSubscriptions();
        List<Long> subscribers = foundUser.getFollower().getSubscribers();

        boolean foundUs = false;

        for (Long subscriptionId : subscriptions) {
            if (subscriptionId.equals(foundUserId)){
                subscriptions.remove(subscriptionId);
                foundUs = true;
                break;
            }
        }
        if (!foundUs) subscriptions.add(foundUserId);


        for (Long subscriber : subscribers) {
            if (subscriber.equals(currentUserId)){
                subscribers.remove(subscriber);
                foundUs = true;
                break;
            }
        }
        if (!foundUs) subscribers.add(currentUserId);


//        for (Long subscriberId : subscribers) {
//            if (subscriberId.equals(currentUserId)){
//                subscribers.remove(subscriberId);
//                foundUs = true;
//                break;
//            }
//        }
//        if (!foundUs) subscribers.add(currentUserId);

    }
}
