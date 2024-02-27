package spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.entity.UserInfo;
import spring.service.UserInfoService;

@Controller
@RequestMapping("/userInfo")
@RequiredArgsConstructor
public class UserInfoApi {
    private final UserInfoService userInfoService;

//
//    @GetMapping("/profile")
//    public String profile(Model model){
//        model.addAttribute("profile",new UserInfo());
//        return "profile";
//    }
//    @GetMapping("/profiles")
//    public String profiles(Model model){
//        model.addAttribute("profiles",new UserInfo());
//        return "profiles";
//    }

    @GetMapping("/update/{userInfoId}")
    public String editStudentsForm(@PathVariable Long userInfoId, Model model) {
        UserInfo userinfo = userInfoService.findById(userInfoId);
        model.addAttribute( "userInfo", userinfo);
        model.addAttribute("userInfoId", userInfoId);
        return "edit-userInfo";
    }
    @PostMapping("/edit/{userInfoId}")
    public String update(@ModelAttribute("userInfo") UserInfo userInfo,
                         @PathVariable Long userInfoId) {
        userInfoService.updateByUserInfoId(userInfoId, userInfo);
        return "redirect:/userInfo/update/{userInfoId}";
    }

}
