/*package com.society.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@ControllerAdvice(annotations = Controller.class)
public class GlobalModelAdvice {

    @ModelAttribute
    public void addLoggedInSecretary(Model model, HttpSession session) {
        Object sec = session.getAttribute("secretary");
        if (sec != null) {
            model.addAttribute("secretary", sec);   
        }
    }
}
*/