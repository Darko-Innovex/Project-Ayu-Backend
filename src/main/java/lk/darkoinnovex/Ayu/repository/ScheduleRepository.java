package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.doctor.id = :doctorId AND s.hospital.id = :hospitalId AND s.date >= CURRENT DATE")
    Optional<List<Schedule>> getScheduleOfDoctorOnHospital(@Param("doctorId") Long doctorId, @Param("hospitalId") Long hospitalId);
}
