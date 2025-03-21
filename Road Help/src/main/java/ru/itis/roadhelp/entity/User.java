package ru.itis.roadhelp.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(length = 300)
    private String passwordHash;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role{
        USER,ADMIN
    }

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State{
        ACTIVE,BANNED
    }

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_comments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private List<Comment> comments = new ArrayList<>();

    public Boolean isActive(){
        return this.state == State.ACTIVE;
    }

    public Boolean isBanned(){
        return this.state == State.BANNED;
    }

    public Boolean isAdmin(){
        return this.role == Role.ADMIN;
    }
}
