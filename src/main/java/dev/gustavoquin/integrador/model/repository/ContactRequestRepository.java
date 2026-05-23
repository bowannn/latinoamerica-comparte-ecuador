package dev.gustavoquin.integrador.model.repository;

import dev.gustavoquin.integrador.model.entity.ContactRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRequestRepository extends JpaRepository<ContactRequest, Long> {
}
