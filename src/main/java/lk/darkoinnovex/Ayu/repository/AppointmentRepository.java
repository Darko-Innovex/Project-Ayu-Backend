package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Appointment;
import lk.darkoinnovex.Ayu.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select a from Appointment a WHERE a.patient=:patient")
    Optional<List<Appointment>> findByPatientId(@Param("patient") Patient patient);
}
