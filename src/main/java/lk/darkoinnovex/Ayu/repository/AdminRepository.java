package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

    @Query("SELECT a FROM Admin a WHERE a.email=:username OR a.nic=:username AND a.password=:password")
    Optional<Admin> findAdminBySignInInfo(@Param("username") String username, @Param("password") String password);
}
