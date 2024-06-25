package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.MedicineList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineListRepository extends JpaRepository<MedicineList, Long> {
}
