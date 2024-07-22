package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Appointment;
import lk.darkoinnovex.Ayu.entity.Hospital;
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
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select a from Appointment a WHERE a.patient=:patient")
    Page<Appointment> findByPatientId(@Param("patient") Patient patient, Pageable pageable);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.status!='Pending' AND a.patient=:patient")
    Optional<Integer> countCompletedAppointments(@Param("patient") Patient patient);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.status='Pending' AND a.patient=:patient")
    Optional<Integer> countPendingAppointments(@Param("patient") Patient patient);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.patient=:patient")
    Optional<Integer> countAppointments(@Param("patient") Patient patient);

    @Query("SELECT a FROM Appointment a WHERE a.patient = :patient AND a.timestamp BETWEEN :startDate AND :endDate")
    Page<Appointment> findAppointmentsByPatientIdAndDateRange(
            @Param("patient") Patient patient,
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate,
            Pageable pageable);

    @Query("select a from Appointment a WHERE a.hospital.id=:hospitalId ORDER BY a.id DESC")
    Page<Appointment> findByHospitalId(@Param("hospitalId") Long hId, Pageable pageable);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.status!='Pending' AND a.hospital=:hospital")
    Optional<Integer> countCompletedAppointmentsOfHospital(@Param("hospital") Hospital hospital);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.status='Pending' AND a.hospital=:hospital")
    Optional<Integer> countPendingAppointmentsOfHospital(@Param("hospital") Hospital hospital);
}
