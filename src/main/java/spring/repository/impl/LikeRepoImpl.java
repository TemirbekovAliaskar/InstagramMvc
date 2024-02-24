package spring.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.entity.Like;
import spring.entity.Post;
import spring.entity.User;
import spring.repository.LikeRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class LikeRepoImpl implements LikeRepository {

    private final EntityManager entityManager;


    @Override
    public void likePost(Long userId, Long postId) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        Post post = entityManager.find(Post.class, postId);


        // Проверяем, был ли уже установлен лайк от данного пользователя для данного поста
        Like existingLike = entityManager.createQuery(
                        "SELECT l FROM Like l WHERE l.user = :user AND l.post = :post", Like.class)
                .setParameter("user", user)
                .setParameter("post", post)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

        // Если лайк уже существует, просто выходим из метода
        if (existingLike != null) {
            return;
        }

        // Создаем новый лайк
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        like.setIsLike(true); // Устанавливаем значение лайка

        // Сохраняем лайк
        entityManager.persist(like);
        entityManager.getTransaction().commit();

    }
}
