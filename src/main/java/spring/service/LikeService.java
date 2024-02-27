package spring.service;

import spring.entity.Like;

public interface LikeService {

    void likePost(Long userId, Long postId);
}
