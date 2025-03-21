package ru.itis.roadhelp.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.roadhelp.dto.CommentDto;
import ru.itis.roadhelp.repositories.CommentRepository;
import ru.itis.roadhelp.repositories.UserRepository;
import ru.itis.roadhelp.security.UserDetailsImpl;
import ru.itis.roadhelp.services.CommentService;
import ru.itis.roadhelp.services.mapper.CommentMapper;
import ru.itis.roadhelp.entity.User;
import ru.itis.roadhelp.form.CommentForm;
import ru.itis.roadhelp.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public List<CommentDto> getCommentsByUser(Long userId) {
        List<Comment> comments = commentRepository.findCommentsByUserId(userId);
        comments.forEach(comment -> Hibernate.initialize(comment.getUsers()));
        return comments.stream()
                .map(comment -> {
                    CommentDto commentDto = new CommentDto();
                    commentDto.setContent(comment.getContent());
                    commentDto.setCreatedAt(comment.getCreatedAt());
                    User commenter = commentRepository.findCommenterByCommentId(comment.getId());
                    Hibernate.initialize(commenter.getComments());
                    commentDto.setAuthor(commenter.getFirstName() + " " + commenter.getLastName());
                    return commentDto;
                })
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void addComment(Long authorId, CommentForm commentForm) {
        User user = userRepository.findById(commentForm.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Recipient not found"));
        if (authorId.equals(user.getId())){
            throw new IllegalArgumentException("You cannot comment on your own profile");
        }
        Comment comment = commentMapper.mapToComment(commentForm);
        comment.setContent(commentForm.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        user.getComments().add(comment);
        Hibernate.initialize(user.getComments());
        userRepository.save(user);
    }
}
