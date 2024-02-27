package spring.repository;

import spring.entity.Post;
import spring.entity.User;

import java.util.List;

public interface PostRepository {

    List<Post> getAll();


    void savePost(Long userId,Post post);
    Post findById(Long id);
    void updatePost(Long id,Post post);
//    void delete(Long id);
    void remove(Long userId, Long postId);
}
