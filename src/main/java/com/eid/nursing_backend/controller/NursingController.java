package com.eid.nursing_backend.controller;

import com.eid.nursing_backend.model.Availability;
import com.eid.nursing_backend.model.NursingReport;
import com.eid.nursing_backend.service.NursingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nurse")
public class NursingController {

    private final NursingService nursingService;

    public NursingController(NursingService nursingService) {
        this.nursingService = nursingService;
    }

    @PreAuthorize("hasRole('NURSE')")
    @PostMapping("/{id}/availability")
    public Availability addAvailability(@PathVariable Long id, @RequestBody Availability availability) {
        return nursingService.addAvailability(id, availability);
    }

    @PreAuthorize("hasRole('NURSE')")
    @PostMapping("/{id}/report")
    public NursingReport addReport(@PathVariable Long id, @RequestBody NursingReport report) {
        return nursingService.addReport(id, report);
    }
}
