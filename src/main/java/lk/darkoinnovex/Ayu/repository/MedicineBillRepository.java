package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.MedicineBill;
import lk.darkoinnovex.Ayu.entity.MedicineList;
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

    @Query("SELECT ml FROM MedicineList ml JOIN Appointment a ON a.medicineBill = ml.medicineBill " +
            "WHERE a.patient = :patient AND " +
            "FUNCTION('DATE_ADD', ml.timestamp, FUNCTION('INTERVAL', ml.dayCount, 'DAY')) >= CURRENT_DATE")
    Optional<List<MedicineList>> getCurrentDrugListOfPatient(@Param("patient") Patient patient);
}
