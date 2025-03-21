package ru.itis.roadhelp.repositories;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import ru.itis.roadhelp.entity.Report;

import java.util.List;

@RequiredArgsConstructor
public class ReportRepositoryCustomImpl implements ReportRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public void deleteReportsByReportedUserId(Long userId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Report> deleteQuery = criteriaBuilder.createCriteriaDelete(Report.class);
        Root<Report> reportRoot = deleteQuery.from(Report.class);
        deleteQuery.where(criteriaBuilder.equal(reportRoot.get("reportedUser").get("id"), userId));
        entityManager.createQuery(deleteQuery).executeUpdate();

    }
}