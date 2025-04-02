package com.example.alpez.Auth;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.alpez.Entity.UserEntity;
import com.example.alpez.Repo.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


   @Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;

    @Autowired
    public OAuth2LoginSuccessHandler(JwtUtil jwtUtil, UserRepo userRepo) {
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        // Check if user exists, if not, create one
        Optional<UserEntity> existingUser = userRepo.findByEmail(email);
        UserEntity user;
if (existingUser.isPresent()) { // ✅ Check if user exists
    user = existingUser.get();  // ✅ Get the user safely
} else {
    user = new UserEntity();
    user.setEmail(email);
    user.setName("vicci");
    userRepo.save(user);
}

        // Generate JWT token for this user
        String jwtToken = jwtUtil.generateToken(user);

        // Send JWT token as response
        response.setHeader("Authorization", "Bearer " + jwtToken);
System.out.println(jwtToken);
        response.sendRedirect("/user/print");
    }
}
