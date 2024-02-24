package spring.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import spring.entity.*;
import spring.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class PostRepoImpl implements PostRepository {

    private final EntityManager entityManager;

    @Override
    public List<Post> getAll() {
       return entityManager.createQuery("select p from Post p", Post.class).getResultList();
    }


    @Override
    public void savePost(Long userId,Post post) {

        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        post.setUser(user);
        entityManager.persist(post);
        entityManager.getTransaction().commit();
    }

    @Override
    public Post findById(Long id) {
        return entityManager.find(Post.class,id);
    }

    @Override
    public void updatePost(Long id, Post newPost) {
        entityManager.getTransaction().begin();
        Post post = entityManager.find(Post.class, id);
        post.setTitle(newPost.getTitle());
        post.setDescription(newPost.getDescription());
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Post post = entityManager.find(Post.class, id);
        entityManager.remove(post);
        entityManager.getTransaction().commit();
    }
}
