package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.HealthCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HealthCardRepository extends JpaRepository<HealthCard, Long> {

    @Query("select h from HealthCard h WHERE h.pinNo=:pin")
    Optional<HealthCard> findByPin(@Param("pin")Long healthCardPin);

    @Query("SELECT h FROM HealthCard h WHERE h.status='Not Reserved'")
    Optional<List<HealthCard>> getNotReservedHealthCard();

    @Query("SELECT hc FROM HealthCard hc WHERE hc.pinNo = :pinNo AND hc.password = :password")
    Optional<HealthCard> findByPinAndPassword(@Param("pinNo")Long pinNo, @Param("password")short password);
}
