package ru.itis.roadhelp.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.roadhelp.dto.EventDto;
import ru.itis.roadhelp.dto.LocationDto;
import ru.itis.roadhelp.form.EventForm;
import ru.itis.roadhelp.entity.Event;
import ru.itis.roadhelp.entity.Reply;
import ru.itis.roadhelp.entity.User;
import ru.itis.roadhelp.repositories.EventRepository;
import ru.itis.roadhelp.repositories.ReplyRepository;
import ru.itis.roadhelp.repositories.UserRepository;
import ru.itis.roadhelp.security.UserDetailsImpl;
import ru.itis.roadhelp.services.EventService;
import ru.itis.roadhelp.services.mapper.EventMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;
    private final EventMapper eventMapper;


    @Override
    public void createEvent(Long authorId, EventForm eventForm) {
        Event event = eventMapper.mapToEvent(eventForm);
        event.setCreatedAt(LocalDateTime.now());
        event.setCreatorId(authorId);
        eventRepository.save(event);
    }

    @Transactional
    @Override
    public List<EventDto> getEvents() {
        return eventRepository.findAll().stream()
                .map(event -> {
                    User creator = userRepository.findById(event.getCreatorId())
                            .orElseThrow(() -> new IllegalArgumentException("User doesn't exists"));
                    Hibernate.initialize(creator.getComments());
                    EventDto eventDto = eventMapper.mapToEventDto(event);
                    eventDto.setCreatorName(creator.getFirstName() + " " + creator.getLastName());
                    Reply reply = replyRepository.findByEventId(event.getId()).stream().findFirst().orElse(null);
                    if (reply != null) {
                        User responder = reply.getResponder();
                        Hibernate.initialize(responder.getComments());
                        eventDto.setResponderName(responder.getFirstName() + " " + responder.getLastName());
                        eventDto.setResponderId(responder.getId());
                        eventDto.setResponseCreatedAt(reply.getCreatedAt());
                    }
                    return eventDto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<EventDto> getEventsByLocation(Long authorId, LocationDto location) {
        double userLatitude = location.getLatitude();
        double userLongitude = location.getLongitude();
        double radius = 50.0; // радиус поиска в километрах

        List<Event> events = eventRepository.findEventsWithinRadius(userLatitude, userLongitude, radius);

        return events.stream()
                .map(event -> {
                    EventDto eventDto = eventMapper.mapToEventDto(event);

                    User creator = userRepository.findById(event.getCreatorId())
                            .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));
                    eventDto.setCreatorName(creator.getFirstName() + " " + creator.getLastName());
                    eventDto.setCurrentUserCreator(event.getCreatorId().equals(authorId));

                    replyRepository.findByEventId(event.getId()).stream().findFirst().ifPresent(reply -> {
                        User responder = reply.getResponder();
                        eventDto.setResponderName(responder.getFirstName() + " " + responder.getLastName());
                        eventDto.setResponderId(responder.getId());
                        eventDto.setResponseCreatedAt(reply.getCreatedAt());
                    });

                    return eventDto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<EventDto> getEventsByUser(Long userId) {
        return eventRepository.findByCreatorId(userId).stream()
                .map(event -> {
                    User creator = userRepository.findById(event.getCreatorId())
                            .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));
                    EventDto eventDto = eventMapper.mapToEventDto(event);
                    eventDto.setCreatorName(creator.getFirstName() + " " + creator.getLastName());
                    Reply reply = replyRepository.findByEventId(event.getId()).stream().findFirst().orElse(null);
                    if (reply != null) {
                        User responder = reply.getResponder();
                        eventDto.setResponderName(responder.getFirstName() + " " + responder.getLastName());
                        eventDto.setResponseCreatedAt(reply.getCreatedAt());
                    }
                    return eventDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEvent(Long eventId) {
        replyRepository.deleteByEventId(eventId);
        eventRepository.deleteById(eventId);
    }

    @Override
    public void updateEventStatus(Long eventId, Event.Status status){
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        event.setStatus(status);
        eventRepository.save(event);
    }

    @Override
    public EventDto getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        return eventMapper.mapToEventDto(event);
    }

    @Override
    public void updateEvent(Long eventId, EventForm eventForm) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        event.setTitle(eventForm.getTitle());
        event.setDescription(eventForm.getDescription());
        event.setContactInfo(eventForm.getContactInfo());
        event.setLatitude(eventForm.getLatitude());
        event.setLongitude(eventForm.getLongitude());
        eventRepository.save(event);
    }
}
