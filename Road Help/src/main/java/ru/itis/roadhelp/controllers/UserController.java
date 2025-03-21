package ru.itis.roadhelp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.itis.roadhelp.security.UserDetailsImpl;
import ru.itis.roadhelp.entity.User;
import ru.itis.roadhelp.services.UserService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    @PreAuthorize("isAuthenticated()")
    public String getUserProfile(@PathVariable Long userId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long currentUserId = userDetails.getUser().getId();
            model.addAttribute("currentUserId", currentUserId);
        }
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("userId", userId);
        return "user/profile";
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String getCurrentUserProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long userId = userDetails.getUser().getId();

            User user = userService.getUserById(userId);
            model.addAttribute("user", user);
            model.addAttribute("userId", userId);
            return "user/profile";
        }
        return "redirect:/signIn";
    }

}
