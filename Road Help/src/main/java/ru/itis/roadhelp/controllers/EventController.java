package ru.itis.roadhelp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.roadhelp.dto.EventDto;
import ru.itis.roadhelp.dto.LocationDto;
import ru.itis.roadhelp.form.EventForm;
import ru.itis.roadhelp.entity.Event;
import ru.itis.roadhelp.security.UserDetailsImpl;
import ru.itis.roadhelp.services.EventService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @GetMapping("/new")
    @PreAuthorize("isAuthenticated()")
    public String createEvent() {
        return "event/create_event";
    }

    @PostMapping("/new")
    @PreAuthorize("isAuthenticated()")
    public String createEvent(@Valid EventForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "event/create_event";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long authorId = userDetails.getUser().getId();
            eventService.createEvent(authorId,form);
        }
        return "event/event_created";
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public String getAllEvents(Model model) {
        List<EventDto> events = eventService.getEvents();
        model.addAttribute("events", events);
        return "event/events";
    }

    @GetMapping("/{userId}/created")
    public String getUserEvents(@PathVariable Long userId, Model model) {
        List<EventDto> events = eventService.getEventsByUser(userId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long currentUserId = userDetails.getUser().getId();
            model.addAttribute("currentUserId", currentUserId);
        }
        model.addAttribute("events", events);
        model.addAttribute("userId", userId);
        return "user/user_events";
    }

    @PostMapping("/{userId}/events/{eventId}/delete")
    @PreAuthorize("isAuthenticated()")
    public String deleteEvent(@PathVariable Long userId, @PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/event/" + userId + "/created";
    }

    @PostMapping("/{userId}/events/{eventId}/close")
    @PreAuthorize("isAuthenticated()")
    public String closeEvent(@PathVariable Long userId, @PathVariable Long eventId) {
        eventService.updateEventStatus(eventId, Event.Status.COMPLETED);
        return "redirect:/event/" + userId + "/created";
    }

    @PostMapping("/{userId}/events/{eventId}/open")
    @PreAuthorize("isAuthenticated()")
    public String openEvent(@PathVariable Long userId, @PathVariable Long eventId) {
        eventService.updateEventStatus(eventId, Event.Status.FREE);
        return "redirect:/event/" + userId + "/created";
    }

    @GetMapping("/{userId}/events/{eventId}/edit")
    @PreAuthorize("isAuthenticated()")
    public String editEvent(@PathVariable Long userId, @PathVariable Long eventId, Model model) {
        EventDto event = eventService.getEventById(eventId);
        model.addAttribute("event", event);
        model.addAttribute("userId", userId);
        return "event/edit_event";
    }

    @PostMapping("/{userId}/events/{eventId}/edit")
    @PreAuthorize("isAuthenticated()")
    public String updateEvent(@PathVariable Long userId, @PathVariable Long eventId, @Valid EventForm eventForm) {
        eventService.updateEvent(eventId, eventForm);
        return "redirect:/event/" + userId + "/created?success";
    }

    @PostMapping("/filter")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<EventDto>> filterEventsByLocation(@RequestBody LocationDto location) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long authorId = userDetails.getUser().getId();
            List<EventDto> filteredEvents = eventService.getEventsByLocation(authorId, location);
            return ResponseEntity.ok(filteredEvents);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
