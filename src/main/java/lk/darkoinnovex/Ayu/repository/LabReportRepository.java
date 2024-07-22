package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.LabReport;
import lk.darkoinnovex.Ayu.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface LabReportRepository extends JpaRepository<LabReport, Long> {
    @Query("select l from LabReport l WHERE l.patient=:patient")
    Page<LabReport> findByPatientId(@Param("patient") Patient patient, Pageable pageable);

    @Query("SELECT COUNT(l) FROM LabReport l WHERE l.patient=:patient")
    Optional<Integer> countLabReportOfPatient(@Param("patient") Patient patient);

    @Query("SELECT l FROM LabReport l WHERE l.patient = :patient AND l.timestamp BETWEEN :startDate AND :endDate")
    Page<LabReport> findLabReportsByPatientIdAndDateRange(
            @Param("patient") Patient patient,
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate,
            Pageable pageable);

//    @Query("SELECT COUNT(l) FROM LabReport l WHERE l.hospital.id=:hospitalId")
//    Optional<Integer> countLabReportsOfHospital(@Param("hospitalId") Long hId);
}
