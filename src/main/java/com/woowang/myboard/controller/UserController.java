package com.woowang.myboard.controller;

import com.woowang.myboard.model.ERole;
import com.woowang.myboard.model.Role;
import com.woowang.myboard.model.User;
import com.woowang.myboard.payload.request.LoginRequest;
import com.woowang.myboard.payload.request.SignupRequest;
import com.woowang.myboard.payload.response.JwtResponse;
import com.woowang.myboard.repository.RoleRepository;
import com.woowang.myboard.security.jwt.JwtUtils;
import com.woowang.myboard.security.service.UserDetailsImpl;
import com.woowang.myboard.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @GetMapping("/signup")
    public String createForm(Model model) {
        model.addAttribute("signUpRequest", new SignupRequest());
        return "user/createUserForm";
    }

    @PostMapping("/signup")
    public String create(@Valid SignupRequest signupRequest, BindingResult bindingResult) {
        log.info("init Post SignUp");

        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors().toString());
            return "user/createUserForm";
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).get());
        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()));
        user.setRoles(roles);
        userDetailsService.join(user);
        return "redirect:/";

    }

    @GetMapping("/signin")
    public String loginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "/user/loginUserForm";
    }

    @PostMapping("signin")
    public String login(@Valid LoginRequest loginRequest, BindingResult bindingResult) {
        log.info("init Post signin");
        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors().toString());
            return "/user/loginUserForm";
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

        return "redirect:/";
    }
}
