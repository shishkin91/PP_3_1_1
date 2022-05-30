package com.example.pp_3_1_1.controller;

import com.example.pp_3_1_1.model.User;
import com.example.pp_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/")
//    public String findAll(Model model) {
//        List<User> users = userService.findAll();
//        model.addAttribute("listUser", users);
//
//        return "index";
//    }
    @GetMapping("/")
    public ModelAndView home() {
        List<User> listUser = userService.findAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listUser", listUser);
        return mav;
    }
    @GetMapping("/new")
    public String newUserForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "new_user";
    }
    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
    @GetMapping("/edit")
    public ModelAndView editUserForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("edit_user");
        User user = userService.findById(id);
        mav.addObject("user", user);

        return mav;
    }
    @GetMapping("/delete")
    public String deleteUserForm(@RequestParam long id) {
        userService.deleteById(id);
        return "redirect:/";
    }
}
