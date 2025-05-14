package com.eid.nursing_backend.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String dayOfWeek; // مثال: الأحد

    private LocalTime fromTime;

    private LocalTime toTime;

    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private User nurse;

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public LocalTime getFromTime() { return fromTime; }
    public void setFromTime(LocalTime fromTime) { this.fromTime = fromTime; }

    public LocalTime getToTime() { return toTime; }
    public void setToTime(LocalTime toTime) { this.toTime = toTime; }

    public User getNurse() { return nurse; }
    public void setNurse(User nurse) { this.nurse = nurse; }
}
