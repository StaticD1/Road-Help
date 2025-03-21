package ru.itis.roadhelp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.roadhelp.entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCreatorId(Long creatorId);

    @Query("SELECT e FROM Event e WHERE e.creatorId = :creatorId AND e.status IN :statuses")
    List<Event> findByCreatorIdAndStatusIn(@Param("creatorId") Long creatorId, @Param("statuses") List<Event.Status> statuses);

    @Query("SELECT e FROM Event e WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(e.latitude)) * cos(radians(e.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(e.latitude)))) <= :radius")
    List<Event> findEventsWithinRadius(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("radius") double radius);
}
