package ru.itis.roadhelp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.roadhelp.services.ReportService;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final ReportService reportService;

    @GetMapping("/admin/reports")
    public String getAllReports(Model model) {
        model.addAttribute("reports", reportService.getAllReports());
        return "admin/reports";
    }

    @PostMapping("/admin/reports/ban/{userId}")
    public String banUser(@PathVariable Long userId) {
        reportService.banUser(userId);
        return "redirect:/admin/reports";
    }
}
