package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.dto.OldPatientDTO;
import lk.darkoinnovex.Ayu.entity.Patient;
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
}
