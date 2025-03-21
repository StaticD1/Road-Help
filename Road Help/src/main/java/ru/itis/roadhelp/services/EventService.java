package ru.itis.roadhelp.services;

import ru.itis.roadhelp.dto.EventDto;
import ru.itis.roadhelp.dto.LocationDto;
import ru.itis.roadhelp.form.EventForm;
import ru.itis.roadhelp.entity.Event;

import java.util.List;

public interface EventService {
    void createEvent(Long authorId, EventForm eventForm);
    List<EventDto> getEvents();
    List<EventDto> getEventsByUser(Long userId);
    void deleteEvent(Long eventId);
    void updateEventStatus(Long eventId, Event.Status status);
    EventDto getEventById(Long eventId);
    void updateEvent(Long eventId, EventForm eventForm);

    List<EventDto> getEventsByLocation(Long authorId, LocationDto location);
}
