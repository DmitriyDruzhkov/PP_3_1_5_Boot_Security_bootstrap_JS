package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String userList(Model model, Principal principal) {
        model.addAttribute("users", userService.findAll());
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("page", "PAGE_ADMIN");
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.getRoles());
        return "admin";
    }

    @GetMapping("/")
    public String createUser(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getRoles());
        return "admin";
    }

    @PostMapping("/")
    public String createUser(User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
