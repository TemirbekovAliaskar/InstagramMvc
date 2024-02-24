package spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.repository.FollowerRepository;
import spring.service.FollowerService;
@Service
@RequiredArgsConstructor
public class FollowerServiceImpl  implements FollowerService {

    private final FollowerRepository followerRepository;

    @Override
    public int subscribers(Long followerId) {
        return followerRepository.subscribers(followerId);
    }

    @Override
    public int subscriptions(Long followerId) {
        return followerRepository.subscriptions(followerId);
    }

    @Override
    public void following(Long userId, Long foundId) {
        followerRepository.following(userId, foundId);
    }
}
