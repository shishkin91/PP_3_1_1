package com.example.pp_3_1_1.controller;

import com.example.pp_3_1_1.model.User;
import com.example.pp_3_1_1.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController { // c jsp так и не удалось настроить
    @Autowired
    private final UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }
    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userServiceImp.saveUser(user);
        return "redirect:/";
    }
    @GetMapping("/")
    public String showUserList(Model model) {
        model.addAttribute("users", userServiceImp.findAll());
        return "index";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = null;
        try {
            user = userServiceImp.findById(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user Id:" + id);
        }

        model.addAttribute("user", user);
        return "update-user";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Validated User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userServiceImp.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = null;
        try {
            user = userServiceImp.findById(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user Id:" + id);
        }
        userServiceImp.deleteById(user.getId());
        return "redirect:/";
    }

/////////////

//    @GetMapping("/")
//    public ModelAndView home() {
//        List<User> listUser = userService.findAll();
//        ModelAndView mav = new ModelAndView("index");
//        mav.addObject("listUser", listUser);
//        return mav;
//    }
//    @GetMapping("/new")
//    public String newUserForm(Map<String, Object> model) {
//        User user = new User();
//        model.put("user", user);
//        return "new_user";
//    }
//    @PostMapping(value = "/save")
//    public String saveUser(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/";
//    }
//    @GetMapping("/edit")
//    public ModelAndView editUserForm(@RequestParam long id) {
//        ModelAndView mav = new ModelAndView("edit_user");
//        User user = userService.findById(id);
//        mav.addObject("user", user);
//
//        return mav;
//    }
//    @GetMapping("/delete")
//    public String deleteUserForm(@RequestParam long id) {
//        userService.deleteById(id);
//        return "redirect:/";
//    }
}
