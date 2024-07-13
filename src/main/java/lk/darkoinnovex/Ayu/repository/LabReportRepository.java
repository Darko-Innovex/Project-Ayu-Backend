package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.LabReport;
import lk.darkoinnovex.Ayu.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabReportRepository extends JpaRepository<LabReport, Long> {
    @Query("select l from LabReport l WHERE l.patient=:patient")
    Page<LabReport> findByPatientId(@Param("patient") Patient patient, Pageable pageable);

    @Query("SELECT COUNT(l) FROM LabReport l WHERE l.patient=:patient")
    Optional<Integer> countLabReportOfPatient(@Param("patient") Patient patient);

}
