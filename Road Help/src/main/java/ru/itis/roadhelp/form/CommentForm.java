package ru.itis.roadhelp.form;

import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Data
public class CommentForm {

    @NotNull(message = "Content cannot be null")
    @NotEmpty(message = "Content cannot be empty")
    @Size(max = 255)
    private String content;

    private Long userId;

}
