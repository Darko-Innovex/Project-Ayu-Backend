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

    @Query("select m from MedicineBill m where m.appointment.id=:appointmentId")
    Optional<MedicineBill> findMedicineBillOfAppointment(@Param("appointmentId") Long appointmentId);
/*
    @Query("SELECT m FROM Appointment a " +
            "JOIN a.medicineBill mb " +
            "JOIN mb.medicine m " +
            "WHERE a.patient = :patient ")
    Optional<List<Medicine>> getCurrentDrugListOfPatient(@Param("patient") Patient patient);*/

}