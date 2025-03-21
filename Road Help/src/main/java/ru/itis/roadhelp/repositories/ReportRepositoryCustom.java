package ru.itis.roadhelp.repositories;

public interface ReportRepositoryCustom {
    void deleteReportsByReportedUserId(Long userId);
}