package com.eid.nursing_backend.model;


import jakarta.persistence.*;


@Entity
public class Role {

    public enum RoleName {
        ADMIN,
        EMPLOYEE,
        NURSE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleName name;

    // Constructors
    public Role() {}

    public Role(RoleName name) {
        this.name = name;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}