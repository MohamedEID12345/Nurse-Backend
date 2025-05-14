package com.eid.nursing_backend.repository;

import com.eid.nursing_backend.model.NursingReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NursingReportRepository extends JpaRepository<NursingReport, Long> {
}
