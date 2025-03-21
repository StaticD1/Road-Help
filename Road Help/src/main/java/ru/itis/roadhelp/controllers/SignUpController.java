package ru.itis.roadhelp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.roadhelp.form.SignUpForm;
import ru.itis.roadhelp.services.SignUpService;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "user/sign_up";
    }

    @PostMapping("/signUp")
    public String signUpUser(@Valid SignUpForm signUpForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "user/sign_up";
        }
        try{
            signUpService.signUp(signUpForm);
            return "redirect:/signIn";
        } catch (IllegalArgumentException e){
            return "user/user_exists";
        }
    }
}
