package ru.itis.roadhelp.services;

import ru.itis.roadhelp.form.ReportForm;
import ru.itis.roadhelp.entity.Report;

import java.util.List;

public interface ReportService {
    public void submitReport(Long authorId, ReportForm reportForm);

    public void banUser(Long userId);

    public List<Report> getAllReports();
}
