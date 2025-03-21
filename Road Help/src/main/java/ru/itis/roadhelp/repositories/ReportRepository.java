package ru.itis.roadhelp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.roadhelp.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryCustom {
}
