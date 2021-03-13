package com.woowang.myboard.controller;

import com.woowang.myboard.model.User;
import com.woowang.myboard.security.service.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j  //로그 하기 위해
public class HomeController {

    @RequestMapping("/")
    public String home(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = ((UserDetailsImpl) principal).getUsername();
//        String email = ((UserDetailsImpl) principal).getEmail();
        log.info(principal != null ? principal.toString() : "no user");
//        if(userDetails != null){
//            model.addAttribute("user", userDetails);
//        }
        return "home";
    }

}
