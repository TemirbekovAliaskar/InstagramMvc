package spring.repository;

import spring.entity.Comment;

public interface CommentRepository {

    void comment(Long userId,Long postId, Comment comment);
    void comment(Long postId, Comment comment);
}
