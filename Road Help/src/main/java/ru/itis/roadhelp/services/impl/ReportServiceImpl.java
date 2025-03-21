package ru.itis.roadhelp.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.roadhelp.form.ReportForm;
import ru.itis.roadhelp.entity.Event;
import ru.itis.roadhelp.entity.Report;
import ru.itis.roadhelp.entity.User;
import ru.itis.roadhelp.repositories.EventRepository;
import ru.itis.roadhelp.repositories.ReplyRepository;
import ru.itis.roadhelp.repositories.ReportRepository;
import ru.itis.roadhelp.repositories.UserRepository;
import ru.itis.roadhelp.security.UserDetailsImpl;
import ru.itis.roadhelp.services.ReportService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ReplyRepository replyRepository;

    @Override
    public void submitReport(Long authorId, ReportForm reportForm) {
        User user = userRepository.findById(reportForm.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Report report = new Report();
        User currentUser = new User();
        currentUser.setId(authorId);
        report.setUser(currentUser);
        report.setReportedUser(user);
        report.setTitle(reportForm.getTitle());
        report.setDescription(reportForm.getDescription());
        report.setCreatedAt(LocalDateTime.now());
        reportRepository.save(report);
    }

    @Override
    @Transactional
    public void banUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setState(User.State.BANNED);

        List<Event> activeEvents = eventRepository.findByCreatorIdAndStatusIn
                (userId, List.of(Event.Status.FREE, Event.Status.IN_PROGRESS));
        activeEvents.forEach(event -> replyRepository.deleteByEventId(event.getId()));
        eventRepository.deleteAll(activeEvents);

        reportRepository.deleteReportsByReportedUserId(userId);
        userRepository.save(user);
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
}
