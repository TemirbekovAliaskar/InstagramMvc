package spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.entity.User;
import spring.service.FollowerService;
import spring.service.UserService;

@Controller
@RequestMapping("/follower")
@RequiredArgsConstructor
public class FollowerApi {
    private final UserService userService;
    private final FollowerService followerService;
    @GetMapping("/all/{userId}")
    public String allUsers(Model model, @PathVariable Long userId){
        model.addAttribute("allUsers",userService.usersAll());
        model.addAttribute("loginUserId", userId);
        return "home-search";
    }
    @GetMapping("/search/{userId}")
    public String searchUserName(Model model, @RequestParam("userName") String userName
            ,@PathVariable Long userId){
        User user = userService.searchUserName(userName);
        model.addAttribute("user",user);
        model.addAttribute("userId",userId);
        return "searchResult";
    }
    @GetMapping("/follow/{currentUserId}/{foundUserId}")
    public String follower(Model model,@PathVariable Long currentUserId,@PathVariable Long foundUserId){
        model.addAttribute("currenUserId",currentUserId);
        model.addAttribute("foundUserId",foundUserId);

        followerService.following(currentUserId,foundUserId);

        model.addAttribute("subscribers",followerService.subscribers(userService.finById(currentUserId).getFollower().getId()));
//        model.addAttribute("subscriptions",followerService.subscriptions(userService.finById(foundUserId).getFollower().getId()));
        return "searchResult";
    }
}
