package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Doctor;
import lk.darkoinnovex.Ayu.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT DISTINCT a.doctor FROM Appointment a WHERE a.patient = :patient AND a.status = 'Completed'")
    Optional<List<Doctor>> findCompletedAppointmentDoctorsByPatientId(@Param("patient") Patient patient);
}
