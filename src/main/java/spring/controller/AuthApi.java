package spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.entity.User;
import spring.service.UserService;

@Controller
@RequestMapping("/instagram")
@RequiredArgsConstructor
public class AuthApi {
    private final UserService userService;
    @GetMapping("/login")
    public String createLogin(Model model){
        model.addAttribute("newUserLogin",new User());
        return "loginUser";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("newUserLogin")User user,Model model){
        try {
            User user1 = userService.login(user);
            if (user1 != null){
                model.addAttribute("user",user1);
                return "/home";
            }else {
                return "error-page";
            }
        }catch (Exception e){
            model.addAttribute("errorMessage","Error email and password");
            return "error-page";
        }
    }
    @GetMapping("/register")
    public String create(Model model){
        model.addAttribute("newUser", new User());
        return "newUser";
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("newUser")User user,Model model){
        try {
            userService.register(user);
            return "redirect:/instagram/login";
        }catch (Exception e){
           model.addAttribute("errorMessage","Duplicate email and userName");
           return "error-page";
        }
    }
}
