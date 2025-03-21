package ru.itis.roadhelp.services.impl;

import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.itis.roadhelp.dto.ReplyDto;
import ru.itis.roadhelp.entity.Event;
import ru.itis.roadhelp.entity.Reply;
import ru.itis.roadhelp.entity.User;
import ru.itis.roadhelp.repositories.EventRepository;
import ru.itis.roadhelp.repositories.ReplyRepository;
import ru.itis.roadhelp.repositories.UserRepository;
import ru.itis.roadhelp.security.UserDetailsImpl;
import ru.itis.roadhelp.services.ReplyService;
import ru.itis.roadhelp.services.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final EventRepository eventRepository;
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;

    @Override
    @Transactional
    public void replyToEvent(Long eventId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User responder = userDetails.getUser();

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));
        event.setStatus(Event.Status.IN_PROGRESS);
        eventRepository.save(event);

        Reply reply = Reply.builder()
                .event(event)
                .responder(responder)
                .createdAt(LocalDateTime.now())
                .build();
        replyRepository.save(reply);
    }

    @Override
    @Transactional
    public List<ReplyDto> getRepliesByUser(Long userId) {
        return replyRepository.findByResponderId(userId).stream()
                .map(reply -> {
                    Event event = eventRepository.findById(reply.getEvent().getId())
                            .orElseThrow(() -> new IllegalArgumentException("Event not found"));
                    ReplyDto replyDto = replyMapper.mapToReplyDto(reply);
                    return replyDto;
                })
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("Reply not found"));
        Event event = reply.getEvent();
        event.setStatus(Event.Status.FREE);
        replyRepository.delete(reply);
        eventRepository.save(event);

    }
}
