package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.LabReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabReportRepository extends JpaRepository<LabReport, Long> {
}
