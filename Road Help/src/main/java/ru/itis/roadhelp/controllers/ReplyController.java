package ru.itis.roadhelp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.roadhelp.dto.ReplyDto;
import ru.itis.roadhelp.security.UserDetailsImpl;
import ru.itis.roadhelp.services.ReplyService;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/respond")
    @PreAuthorize("isAuthenticated()")
    public String respondToEvent(@RequestParam Long eventId) {
        replyService.replyToEvent(eventId);
        return "redirect:/event/all";
    }

    @GetMapping("/{userId}/replies")
    public String getUserReplies(@PathVariable Long userId, Model model) {
        List<ReplyDto> replies = replyService.getRepliesByUser(userId);
        model.addAttribute("replies", replies);
        model.addAttribute("userId", userId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long currentUserId = userDetails.getUser().getId();
            model.addAttribute("currentUserId", currentUserId);
        }
        return "user/user_replies";
    }

    @PostMapping("/delete/{replyId}")
    @PreAuthorize("isAuthenticated()")
    public String deleteReply(@PathVariable Long replyId, @RequestParam Long userId) {
        replyService.deleteReply(replyId);
        return "redirect:/reply/" + userId + "/replies";
    }
}
