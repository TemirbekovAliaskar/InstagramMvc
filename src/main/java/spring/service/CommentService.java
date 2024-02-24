package spring.service;

import spring.entity.Comment;

public interface CommentService {
    void comment(Long userId,Long postId, Comment comment);
    void comment(Long postId, Comment comment);
}
