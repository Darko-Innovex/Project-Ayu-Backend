package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.LabReport;
import lk.darkoinnovex.Ayu.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabReportRepository extends JpaRepository<LabReport, Long> {
    @Query("select l from LabReport l WHERE l.patient=:patient")
    Optional<List<LabReport>> findByPatientId(@Param("patient") Patient patient);
}
