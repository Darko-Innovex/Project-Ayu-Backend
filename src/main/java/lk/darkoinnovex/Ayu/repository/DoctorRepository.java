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

    @Query("SELECT d FROM Doctor d WHERE d.nic=:nic AND d.password=:password")
    Optional<Doctor> findDoctorBySignInInfo(@Param("nic") String nic, @Param("password") String password);

    @Query("SELECT d FROM Doctor d " +
            "JOIN DoctorList dl ON d.id = dl.doctor.id " +
            "WHERE dl.hospital.id = :hospitalId " +
            "AND d.speciality = :speciality")
    Optional<List<Doctor>> findDoctorsByHospitalAndSpeciality(
            @Param("hospitalId") Long hospitalId, @Param("speciality") String speciality);
}
