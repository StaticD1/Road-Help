package ru.itis.roadhelp.services.mapper;

import org.mapstruct.Mapper;
import ru.itis.roadhelp.dto.CommentDto;
import ru.itis.roadhelp.form.CommentForm;
import ru.itis.roadhelp.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment mapToComment(CommentForm commentForm);

    CommentDto mapToCommentDto(Comment comment);
}
