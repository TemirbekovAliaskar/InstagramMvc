package spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.entity.Comment;
import spring.service.CommentService;
import spring.service.PostService;
import spring.service.UserService;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentApi {
    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;
    @GetMapping("/newComment/{userId}/{postId}")
    public String commentForm(Model model, @PathVariable Long userId, @PathVariable Long postId) {
        model.addAttribute("comments", new Comment());
        model.addAttribute("userId", userService.finById(userId).getId());
        model.addAttribute("findPost",postService.findById(postId));
        model.addAttribute("postId", postId);
        return "commentForm";
    }

    @PostMapping("/saveComment/{userId}/{postId}")
    public String commentSave(@ModelAttribute("newComment") Comment comment,
                              @PathVariable Long userId, @PathVariable Long postId) {
        commentService.comment(userId, postId, comment);
        return "commentForm";
    }
}

//    @GetMapping("/newComment/{postId}")
//    public String viewComment(Model model,@PathVariable Long postId){
//        Post findPost = postService.findById(postId);
//        model.addAttribute("findPost",findPost);
//        model.addAttribute("newComment",new Comment());
//        return "commentForm";
//    }
//
//
//    @PostMapping ("/saveComment/{userId}/{postId}")
//    public String saveComment(@PathVariable Long postId,@ModelAttribute ("newComment") Comment comment,Model model){
//        model.addAttribute("postId",postId);
//        commentService.comment(postId, comment);
//        return "redirect:/comments/newComment/" + postId;
//    }
//
//}
