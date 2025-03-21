package ru.itis.roadhelp.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.roadhelp.entity.Event.Status;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {

    private Long id;
    private String title;
    private String description;
    private Double latitude;
    private Double longitude;
    private String contactInfo;
    private Status status;
    private LocalDateTime createdAt;
    private Long creatorId;
    private String creatorName;
    private String responderName;
    private Long responderId;
    private LocalDateTime responseCreatedAt;
    private boolean isCurrentUserCreator;
}
