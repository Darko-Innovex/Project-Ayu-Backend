package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Allergy;
import lk.darkoinnovex.Ayu.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    @Query("select a from Allergy a WHERE a.patient=:patient")
    Optional<List<Allergy>> findByPatientId(@Param("patient") Patient patient);
}
