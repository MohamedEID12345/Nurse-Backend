package com.eid.nursing_backend.dto;

import com.eid.nursing_backend.model.Role;

public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private Role.RoleName role;

    // Getters and Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role.RoleName getRole() { return role; }
    public void setRole(Role.RoleName role) { this.role = role; }
}
