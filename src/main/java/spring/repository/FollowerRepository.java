package spring.repository;

import spring.entity.User;

public interface FollowerRepository {

    int subscribers(Long followerId);
    int subscriptions(Long followerId);

    void following(Long userId,Long foundId);
}
