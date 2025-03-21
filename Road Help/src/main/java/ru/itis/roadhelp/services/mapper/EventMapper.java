package ru.itis.roadhelp.services.mapper;

import org.springframework.stereotype.Component;
import ru.itis.roadhelp.dto.EventDto;
import ru.itis.roadhelp.form.EventForm;
import ru.itis.roadhelp.entity.Event;

@Component
public class EventMapper {
    public Event mapToEvent (EventForm eventForm) {
        return Event.builder()
                .title(eventForm.getTitle())
                .description(eventForm.getDescription())
                .latitude(eventForm.getLatitude())
                .longitude(eventForm.getLongitude())
                .contactInfo(eventForm.getContactInfo())
                .status(Event.Status.FREE)
                .build();
    }
    public EventDto mapToEventDto (Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .latitude(event.getLatitude())
                .longitude(event.getLongitude())
                .contactInfo(event.getContactInfo())
                .status(event.getStatus())
                .createdAt(event.getCreatedAt())
                .creatorId(event.getCreatorId())
                .build();
    }
}
