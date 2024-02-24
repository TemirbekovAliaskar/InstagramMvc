package spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.entity.Comment;
import spring.repository.CommentRepository;
import spring.service.CommentService;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public void comment(Long postId,Long userId, Comment comment) {
        commentRepository.comment(postId,userId,comment);
    }

    @Override
    public void comment(Long postId, Comment comment) {

        commentRepository.comment(postId,comment);
    }
}
