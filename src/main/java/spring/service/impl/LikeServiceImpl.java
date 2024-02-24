package spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.repository.LikeRepository;
import spring.service.LikeService;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    @Override
    public void likePost(Long userId, Long postId) {
likeRepository.likePost(userId, postId);
    }
}
