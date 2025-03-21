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
import ru.itis.roadhelp.form.ReportForm;
import ru.itis.roadhelp.security.UserDetailsImpl;
import ru.itis.roadhelp.services.ReportService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
@PreAuthorize("isAuthenticated()")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/{userId}/report/new")
    public String newReport(@PathVariable Long userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("reportForm", new ReportForm());
        return "user/new_report";
    }

    @PostMapping("/{userId}/report")
    public String submitReport(@PathVariable Long userId, @Valid @ModelAttribute ReportForm reportForm,
                               BindingResult bindingResult, Model model) {
        model.addAttribute("userId", userId);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult);
            return "user/new_report";
        }
        reportForm.setUserId(userId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long authorId = userDetails.getUser().getId();
            reportService.submitReport(authorId, reportForm);
        }
        return "user/report_success";
    }
}
