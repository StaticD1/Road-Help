package ru.itis.roadhelp.services;

import ru.itis.roadhelp.dto.ReplyDto;

import java.util.List;

public interface ReplyService {
    void replyToEvent(Long eventId);
    List<ReplyDto> getRepliesByUser(Long userId);

    void deleteReply(Long replyId);
}
