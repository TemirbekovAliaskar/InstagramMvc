package spring.service;

import spring.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAll();
    void savePost(Long userId,Post post);
    Post findById(Long id);
    void updatePost(Long id,Post post);
    void delete(Long id);
}
