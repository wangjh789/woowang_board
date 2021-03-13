package com.woowang.myboard.security.service;

import com.woowang.myboard.model.User;
import com.woowang.myboard.payload.response.MessageResponse;
import com.woowang.myboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void join(User user){
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User already Exist");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already Exist");
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}
