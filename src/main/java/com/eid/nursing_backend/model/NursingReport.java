package com.eid.nursing_backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class NursingReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caseName;
    private LocalDateTime workTime;
    private String description;
    private String location;
    private BigDecimal expenses;
    private BigDecimal totalCost;

    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private User nurse;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCaseName() { return caseName; }
    public void setCaseName(String caseName) { this.caseName = caseName; }

    public LocalDateTime getWorkTime() { return workTime; }
    public void setWorkTime(LocalDateTime workTime) { this.workTime = workTime; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public BigDecimal getExpenses() { return expenses; }
    public void setExpenses(BigDecimal expenses) { this.expenses = expenses; }

    public BigDecimal getTotalCost() { return totalCost; }
    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }

    public User getNurse() { return nurse; }
    public void setNurse(User nurse) { this.nurse = nurse; }
}
