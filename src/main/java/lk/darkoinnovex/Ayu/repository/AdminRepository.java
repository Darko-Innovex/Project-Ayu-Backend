package lk.darkoinnovex.Ayu.repository;

import lk.darkoinnovex.Ayu.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
