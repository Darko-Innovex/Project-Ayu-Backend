package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.HealthCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthCardRepository extends JpaRepository<HealthCard, Long> {
}
