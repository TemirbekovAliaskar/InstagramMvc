package spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.entity.Like;
import spring.repository.LikeRepository;
import spring.service.LikeService;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;

    @Override
    public void likePost(Long userId, Long postId) {
        Like like = new Like();
        like.setIsLike(true);
        likeRepository.likePost(userId, postId, like);
    }
}
