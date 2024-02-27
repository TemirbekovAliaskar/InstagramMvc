package spring.repository;

import spring.entity.Like;

public interface LikeRepository {
    void likePost(Long userId, Long postId, Like like);
}
