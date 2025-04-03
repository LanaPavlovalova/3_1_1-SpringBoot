package com.lanapavlova.__1_1.SpringBoot.controller;

import com.lanapavlova.__1_1.SpringBoot.entity.User;
import com.lanapavlova.__1_1.SpringBoot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("user") User user,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/edit")
    public String editUser(@Valid @ModelAttribute("user") User user,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "edit-user";
        }
        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}