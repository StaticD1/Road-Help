package ru.itis.roadhelp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.roadhelp.dto.CommentDto;
import ru.itis.roadhelp.form.CommentForm;
import ru.itis.roadhelp.security.UserDetailsImpl;
import ru.itis.roadhelp.services.CommentService;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{userId}/comments/add")
    @PreAuthorize("isAuthenticated()")
    public String newComment(@PathVariable Long userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("commentForm", new CommentForm());
        return "comment/new_comment";
    }

    @PostMapping("/{userId}/comments")
    @PreAuthorize("isAuthenticated()")
    public String newComment(@PathVariable Long userId, @Valid CommentForm commentForm,
                             BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userId", userId);
            model.addAttribute("commentForm" ,commentForm);
            return "comment/new_comment";
        }
        commentForm.setUserId(userId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long authorId = userDetails.getUser().getId();
            commentService.addComment(authorId, commentForm);
        }
        return "redirect:/profile/" + userId;
    }

    @GetMapping("/{userId}/comments")
    public String getUserComments(@PathVariable Long userId, Model model) {
        List<CommentDto> comments = commentService.getCommentsByUser(userId);
        model.addAttribute("comments", comments);
        model.addAttribute("userId", userId);
        return "user/user_comments";
    }
}

