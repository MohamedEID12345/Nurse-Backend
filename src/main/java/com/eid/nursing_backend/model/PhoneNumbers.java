package com.eid.nursing_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "phone_numbers")
public class PhoneNumbers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(name = "description")
    private String description; // وصف اختياري (مثلاً: "رقم واتساب رئيسي")

    public PhoneNumbers(Long id, String number, String description) {
        this.id = id;
        this.number = number;
        this.description = description;
    }

    public PhoneNumbers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
