package org.aston.dataanalyzeservice.repository;

import org.aston.dataanalyzeservice.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
}
