package com.eid.nursing_backend.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;         // 👈 الاسم
    private Integer age;         // 👈 السن

    private LocalDate date;
    private LocalTime time;

    private String phoneNumber;
    private String description;
    private String address;

    @ManyToOne
    @JoinColumn(name = "whatsapp_number_id")
    private PhoneNumbers whatsappNumber;


    public Booking(Long id, String name, Integer age, LocalDate date, LocalTime time, String phoneNumber, String description, String address, PhoneNumbers whatsappNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.date = date;
        this.time = time;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.address = address;
        this.whatsappNumber = whatsappNumber;
    }

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PhoneNumbers getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(PhoneNumbers whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }
}

