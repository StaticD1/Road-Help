package ru.itis.roadhelp.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private double latitude;

    private double longitude;

    @Column(name = "contact_info")
    private String contactInfo;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "creator_id")
    private Long creatorId;

    public enum Status {
        FREE,
        IN_PROGRESS,
        COMPLETED
    }

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Boolean isFree(){
        return this.status == Event.Status.FREE;
    }
    public Boolean isInProgress(){
        return this.status == Status.IN_PROGRESS;
    }
    public Boolean isCompleted(){
        return this.status == Status.COMPLETED;
    }
}

