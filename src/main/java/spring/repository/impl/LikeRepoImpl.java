package spring.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @PersistenceContext
    private final EntityManager entityManager;

        @Override
        public void likePost(Long userId, Long postId,Like like) {
            Post post = entityManager.find(Post.class, postId);
            User user = entityManager.find(User.class, userId);
            boolean islike = true;
            for (Like like1: post.getLikes()){
                if(like1.getUser().getId().equals(userId)){
                    like1.setIsLike(false);
                    like1.setUser(null);
                    like1.setPost(null);
                    post.getLikes().remove(like1);
                    entityManager.remove(like1);
                    islike = false;
                    break;
                }
            }
            if (islike){like.setUser(user);
                post.addLike(like);
                like.setPost(post);
                entityManager.persist(like);}
        }
}
