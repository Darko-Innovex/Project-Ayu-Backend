package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {
}
