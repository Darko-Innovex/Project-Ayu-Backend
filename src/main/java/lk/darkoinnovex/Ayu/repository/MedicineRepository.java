package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
