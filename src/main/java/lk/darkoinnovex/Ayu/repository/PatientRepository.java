package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.dto.OldPatientDTO;
import lk.darkoinnovex.Ayu.entity.HealthCard;
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
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT new lk.darkoinnovex.Ayu.dto.OldPatientDTO(p.name, a.timestamp, a.appointmentNo) FROM Appointment a JOIN a.patient p WHERE a.doctor.id=:id")
    Optional<List<OldPatientDTO>> getOldPatientsOfDoctor(@Param("id") Long id);

    @Query("SELECT p FROM Patient p WHERE p.healthCard=:healthCard")
    Optional<Patient> getPatientByHealthCard(@Param("healthCard") HealthCard healthCard);

    @Query("SELECT p FROM Patient p WHERE p.nic=:username OR p.email=:username AND p.password=:password")
    Optional<Patient> findPatientBySignInInfo(@Param("username") String username, @Param("password") String password);

    @Query("SELECT p FROM Patient p WHERE p.hospital.id=:hospitalId")
    Page<Patient> getPatientSavedByHospital(@Param("hospitalId") Long hospitalId, Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE p.nic = :nic")
    Optional<Patient> getPatientByNic(@Param("nic") String nic);
}
