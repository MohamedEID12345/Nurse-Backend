package com.eid.nursing_backend.service;

import com.eid.nursing_backend.model.Availability;
import com.eid.nursing_backend.model.NursingReport;
import com.eid.nursing_backend.model.User;
import com.eid.nursing_backend.repository.AvailabilityRepository;
import com.eid.nursing_backend.repository.NursingReportRepository;
import com.eid.nursing_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NursingService {

    private final AvailabilityRepository availabilityRepository;
    private final NursingReportRepository reportRepository;
    private final UserRepository userRepository;

    public NursingService(AvailabilityRepository availabilityRepository,
                          NursingReportRepository reportRepository,
                          UserRepository userRepository) {
        this.availabilityRepository = availabilityRepository;
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    public Availability addAvailability(Long nurseId, Availability availability) {
        Optional<User> nurse = userRepository.findById(nurseId);
        if (nurse.isEmpty()) throw new RuntimeException("Nurse not found");
        availability.setNurse(nurse.get());
        return availabilityRepository.save(availability);
    }

    public NursingReport addReport(Long nurseId, NursingReport report) {
        Optional<User> nurse = userRepository.findById(nurseId);
        if (nurse.isEmpty()) throw new RuntimeException("Nurse not found");
        report.setNurse(nurse.get());
        return reportRepository.save(report);
    }
}
