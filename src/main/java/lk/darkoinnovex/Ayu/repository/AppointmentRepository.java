package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
