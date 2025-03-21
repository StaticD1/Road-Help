package ru.itis.roadhelp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.roadhelp.entity.Comment;
import ru.itis.roadhelp.entity.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.id IN (SELECT cm.id FROM User u JOIN u.comments cm WHERE u.id = :userId)")
    List<Comment> findCommentsByUserId(@Param("userId") Long userId);

    @Query("select u from User u join fetch u.comments c where c.id = :commentId")
    User findCommenterByCommentId(@Param("commentId") Long commentId);
}
