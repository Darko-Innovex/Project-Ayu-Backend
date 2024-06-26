package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.HealthCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthCardRepository extends JpaRepository<HealthCard, Long> {

    @Query("select h from HealthCard h WHERE h.pinNo=:pin")
    Optional<HealthCard> findByPin(@Param("pin")Long healthCardPin);
}
