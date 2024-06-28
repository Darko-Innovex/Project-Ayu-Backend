package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.MedicalReport;
import lk.darkoinnovex.Ayu.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {

    @Query("select a.medicalReport from Appointment a WHERE a.id=:id")
    Optional<MedicalReport> findByAppoinmentId(@Param("id") Long id);

    @Query("select a.medicalReport from Appointment a WHERE a.patient=:patient")
    List<MedicalReport> findByPatientId(@Param("patient") Patient patient);
}
