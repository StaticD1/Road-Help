package ru.itis.roadhelp.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.itis.roadhelp.exception.BannedUserException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex, Model model) {
        return "user/access_denied";
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String handleBadCredentialsException(BadCredentialsException ex, Model model) {
        return "user/bad_credentials";
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public String handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex, Model model) {
        return "user/user_not_found";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleValidationExceptions(ConstraintViolationException ex, Model model) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());
        model.addAttribute("errors", errors);
        return "user/validation_error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException ex, Model model) {
        model.addAttribute("errorMessage", "The page you are looking for does not exist.");
        return "user/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Error: " + ex.getMessage());
        return "error/error";
    }

    @ExceptionHandler(BannedUserException.class)
    public String handleBannedUserException(BannedUserException ex, Model model) {
        model.addAttribute("errorMessage", "You don't have access");
        return "user/403";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorMessage", "Wrong arguments");
        return "comment/error_argument";
    }
}