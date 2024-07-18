package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    @Query("SELECT h FROM Hospital h WHERE h.email=:username AND h.password=:password")
    Optional<Hospital> findHospitalBySignInInfo(String username, String password);

    @Query("SELECT DISTINCT(h.location) FROM Hospital h")
    Optional<List<String>> findAllHospitalsLocations();

    @Query("SELECT h FROM Hospital h WHERE h.location = :location")
    Optional<List<Hospital>> findAllHospitalByLocation(String location);
}
