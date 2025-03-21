package ru.itis.roadhelp.services.mapper;

import org.springframework.stereotype.Component;
import ru.itis.roadhelp.dto.ReplyDto;
import ru.itis.roadhelp.entity.Reply;

@Component
public class ReplyMapper {

    public ReplyDto mapToReplyDto(Reply reply) {
        return ReplyDto.builder()
                .id(reply.getId())
                .eventId(reply.getEvent().getId())
                .eventTitle(reply.getEvent().getTitle())
                .eventCreatedAt(reply.getEvent().getCreatedAt())
                .eventDescription(reply.getEvent().getDescription())
                .responderId(reply.getResponder().getId())
                .responderName(reply.getResponder().getFirstName() + " " + reply.getResponder().getLastName())
                .createdAt(reply.getCreatedAt())
                .latitude(reply.getEvent().getLatitude())
                .longitude(reply.getEvent().getLongitude())
                .status(reply.getEvent().getStatus())
                .build();
    }
}
