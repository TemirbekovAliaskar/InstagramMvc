package spring.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.entity.Comment;
import spring.entity.Post;
import spring.entity.User;
import spring.repository.CommentRepository;


@Repository
@RequiredArgsConstructor
@Transactional
public class CommentRepoImpl implements CommentRepository {

    private final EntityManager entityManager;
    @Override
    public void comment(Long userId,Long postId,Comment comment) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        Post post = entityManager.find(Post.class, postId);
        user.addComment(comment);
        post.addComment(comment);
        comment.setPost(post);
        comment.setUser(user);
        entityManager.persist(comment);
        entityManager.getTransaction().commit();
    }

    @Override
    public void comment(Long postId, Comment comment) {
        entityManager.getTransaction().begin();
        Post post = entityManager.find(Post.class, postId);
        comment.setPost(post);
        post.getComments().add(comment);
        entityManager.persist(comment);
        entityManager.getTransaction().commit();
    }
}
