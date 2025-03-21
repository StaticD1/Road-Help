package ru.itis.roadhelp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.roadhelp.entity.Reply;

import java.util.List;

    public interface ReplyRepository extends JpaRepository<Reply, Long> {
        List<Reply> findByEventId(Long eventId);
        List<Reply> findByResponderId(Long responderId);
        void deleteByEventId(Long eventId);
    }
