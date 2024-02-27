    package spring.controller;


    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import spring.entity.Like;
    import spring.entity.Post;

    import spring.entity.User;
    import spring.service.LikeService;
    import spring.service.PostService;
    import spring.service.UserService;


    @Controller
    @RequestMapping("/post")
    @RequiredArgsConstructor
    public class PostApi {
        private final PostService postService;
        private final UserService userService;
        private final LikeService likeService;




        @GetMapping("/all/{userId}")
        public String getAllPost(Model model, @PathVariable String userId){
            model.addAttribute("allPost",postService.getAll());
            model.addAttribute("loginUserId", userId);
//            model.addAttribute("userId",userService.finById(userId));
            return "home-post";
        }

        @GetMapping("/details/{userId}")
        public String showPostDetails(@PathVariable Long userId, Model model) {
            User user = userService.finById(userId);
            model.addAttribute("postInfo",user.getPosts());
            return "publication";
        }

            @GetMapping("/new/{userId}")
            public String create(Model model,@PathVariable Long userId){
                model.addAttribute("newPost",new Post());
                model.addAttribute("userId",userId);
                return "new-post";
            }
            @PostMapping("/save/{userId}")
            public String save(@ModelAttribute("newPost")Post post,
                               @PathVariable Long userId){
                postService.savePost(userId,post);
                return "redirect:/post/details/{userId}";
            }

        @GetMapping("/update/{postId}")
        public String editStudentsForm(@PathVariable Long postId, Model model){
            Post post = postService.findById(postId);
            model.addAttribute("post",post);
            model.addAttribute("postID", postId);
            return "post-edit";
        }

        @PostMapping("/edit/{postId}")
        public String update(@ModelAttribute("post") Post post,
                             @PathVariable Long postId){
            postService.updatePost(postId, post);
            return "redirect:/post/update/{postId}";
        }


        @GetMapping("/delete/{userId}/{postId}")
        public String delete(@PathVariable Long userId,@PathVariable Long postId,Model model){
            Long userID = userService.finById(userId).getId();
            Long postID = postService.findById(postId).getId();

            model.addAttribute("userId",userService.finById(userId).getId());
            model.addAttribute("postId",postService.findById(postId).getId());
            postService.remove(userID, postID);
            return "redirect:/post/details/{userId}";
        }


        @GetMapping("/likes/{userId}/{postId}")
        public String likes(@PathVariable Long postId,@PathVariable Long userId){
//            model.addAttribute("userId",userService.finById(userId).getId());
//            model.addAttribute("postId",postService.findById(postId).getId());
            likeService.likePost(userId,postId);
            return "home-post";
        }


//        @GetMapping("/like/{postId}")
//        public String isLike(Model model, @PathVariable Long postId){
//            likeService.saveLike(currentUser.getId(),postId);
////         model.addAttribute("countLike",likeService.contLike(postId));
////         model.addAttribute("likePostUser",likeService.getLikes());
////         model.addAttribute("allPost",postService.getAllPost());
//            return "redirect:/post/all";
//        }






    }

