package ru.itis.roadhelp.services;

import ru.itis.roadhelp.dto.CommentDto;
import ru.itis.roadhelp.form.CommentForm;

import java.util.List;

public interface CommentService {

    public List<CommentDto> getCommentsByUser(Long userId);


    public void addComment(Long authorId, CommentForm commentForm);

}
