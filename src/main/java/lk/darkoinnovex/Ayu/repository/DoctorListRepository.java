package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.DoctorList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorListRepository extends JpaRepository<DoctorList, Long> {

    @Query("SELECT dl FROM DoctorList dl WHERE dl.doctor.id = :dId AND dl.hospital.id = :hId")
    Optional<DoctorList> selectByDoctorIdAndHospitalId(@Param("dId") Long dId, @Param("hId") Long hId);
}
