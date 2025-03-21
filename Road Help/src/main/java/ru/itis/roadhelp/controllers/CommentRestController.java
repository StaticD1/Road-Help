package ru.itis.roadhelp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itis.roadhelp.dto.CommentDto;
import ru.itis.roadhelp.form.CommentForm;
import ru.itis.roadhelp.security.UserDetailsImpl;
import ru.itis.roadhelp.services.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
@Tag(name = "Comments API", description = "API for managing user comments")
public class CommentRestController {

    private final CommentService commentService;

    @Operation(summary = "Add a new comment to a user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment successfully added",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PostMapping("/{userId}")
    public ResponseEntity<?> addComment(@PathVariable Long userId, @Valid @RequestBody CommentForm commentForm) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long authorId = userDetails.getUser().getId();
            commentForm.setUserId(userId);
            commentService.addComment(authorId, commentForm);
            return ResponseEntity.ok("Comment added successfully");
        }
        return ResponseEntity.status(403).body("User not authenticated");
    }

    @Operation(summary = "Get all comments for a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/{userId}")
    public ResponseEntity<List<CommentDto>> getCommentsByUser(@PathVariable Long userId) {
        List<CommentDto> comments = commentService.getCommentsByUser(userId);
        return ResponseEntity.ok(comments);
    }
}
