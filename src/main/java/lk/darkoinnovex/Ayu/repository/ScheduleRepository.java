package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
