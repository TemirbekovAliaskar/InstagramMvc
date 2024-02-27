package spring.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.entity.*;
import spring.repository.PostRepository;
import spring.repository.UserRepository;
import spring.service.PostService;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public List<Post> getAll() {
        return postRepository.getAll();
    }

    @Override
    @Transactional
    public void savePost(Long userId, Post post) {
        String imageURL = post.getImageURL();
        Image image = new Image();
        image.setImageURL(imageURL);
        image.setPost(post);
        post.addImage(image);

        postRepository.savePost(userId, post);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void updatePost(Long id, Post post) {
        postRepository.updatePost(id, post);
    }


    @Override
    @Transactional
    public void remove(Long userId, Long postId) {
        Post post = findById(postId);
        User user = userRepository.findById(userId);
        post.setUser(null);
        user.getPosts().remove(post);
        postRepository.remove(userId, postId);
    }
}
