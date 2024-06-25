package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.DoctorList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorListRepository extends JpaRepository<DoctorList, Long> {
}
