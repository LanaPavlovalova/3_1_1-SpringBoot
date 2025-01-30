package com.lanapavlova.__1_1.SpringBoot.controller;


import com.lanapavlova.__1_1.SpringBoot.entity.User;
import com.lanapavlova.__1_1.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/add-user")
    public String showAddUserForm() {
        return "add-user";
    }

    @PostMapping("/add-user")
    public String addUser(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit-user")
    public String showEditUserForm(@RequestParam Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/edit-user")
    public String editUser(@RequestParam Long id, @RequestParam String name, @RequestParam int age) {
        User user = userService.findById(id);
        user.setName(name);
        user.setAge(age);
        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}