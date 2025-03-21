package ru.itis.roadhelp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.roadhelp.entity.Event;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDto {

    private Long id;
    private Long eventId;
    private LocalDateTime eventCreatedAt;
    private Long responderId;
    private String responderName;
    private String eventTitle;
    private String eventDescription;
    private LocalDateTime createdAt;
    private Double latitude;
    private Double longitude;
    private Event.Status status;

}
