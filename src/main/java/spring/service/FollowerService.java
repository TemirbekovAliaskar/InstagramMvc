package spring.service;

import spring.entity.User;

public interface FollowerService {

    int subscribers(Long followerId);
    int subscriptions(Long followerId);
    void following(Long userId,Long foundId);
}
