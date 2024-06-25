package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
