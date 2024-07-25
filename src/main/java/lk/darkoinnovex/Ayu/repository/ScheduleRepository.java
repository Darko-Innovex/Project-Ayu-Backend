package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.doctor.id = :doctorId AND s.hospital.id = :hospitalId AND s.date >= CURRENT DATE")
    Page<Schedule> getScheduleOfDoctorOnHospital(@Param("doctorId") Long doctorId, @Param("hospitalId") Long hospitalId, Pageable pageable);

    @Query("SELECT s FROM Schedule s WHERE s.hospital.id = :hospitalId ORDER BY s.id DESC")
    Page<Schedule> getScheduleOfHospital(@Param("hospitalId") Long hospitalId, Pageable pageable);

    @Query("SELECT s FROM Schedule s WHERE s.doctor.id = :doctorId AND s.date = CURRENT_DATE")
    Optional<List<Schedule>> findByHospitalAndDoctorOnCurrentDate(@Param("doctorId") Long doctorId);
}
