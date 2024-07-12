package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.MedicineBill;
import lk.darkoinnovex.Ayu.entity.Medicine;
import lk.darkoinnovex.Ayu.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineBillRepository extends JpaRepository<MedicineBill, Long> {

    @Query("select a.medicineBill from Appointment a where a.id=:appointmentId")
    Optional<MedicineBill> findMedicineBillOfAppointment(@Param("appointmentId") Long appointmentId);

    @Query(value = "SELECT m.* FROM Medicine m " +
            "JOIN Appointment a ON a.medicineBill = m.medicineBill " +
            "WHERE a.patient = :patient AND " +
            "DATE_ADD(m.timestamp, INTERVAL m.dayCount DAY) >= CURRENT_DATE",
            nativeQuery = true)
    Optional<List<Medicine>> getCurrentDrugListOfPatient(@Param("patient") Patient patient);
}
