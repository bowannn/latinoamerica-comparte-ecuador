package dev.gustavoquin.integrador.model.repository;

import dev.gustavoquin.integrador.model.entity.Testimony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TestimonyRepository extends JpaRepository<Testimony, Long> {
    List<Testimony> findByApprovedFalse();
    List<Testimony> findByApprovedTrue();
}