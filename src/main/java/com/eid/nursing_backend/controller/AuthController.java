package com.eid.nursing_backend.controller;

import com.eid.nursing_backend.dto.AuthRequest;
import com.eid.nursing_backend.dto.AuthResponse;
import com.eid.nursing_backend.dto.RegisterRequest;
import com.eid.nursing_backend.model.User;
import com.eid.nursing_backend.security.JwtUtil;
import com.eid.nursing_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            String token = jwtUtil.generateToken(request.getEmail());

            // جلب المستخدم لمعرفة دوره
            var user = userService.findByEmail(request.getEmail());
            if (user == null) {
                throw new RuntimeException("User not found");
            }

            // يفترض أن المستخدم له دور واحد فقط - نأخذ أول دور من المجموعة
            String role = user.getRoles().stream()
                    .findFirst()
                    .map(r -> r.getName().name())
                    .orElse("UNKNOWN");

            return new AuthResponse(token, role);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }
    }
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public User registerUser(@RequestBody RegisterRequest request) {
        return userService.registerUser(
                request.getFullName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
