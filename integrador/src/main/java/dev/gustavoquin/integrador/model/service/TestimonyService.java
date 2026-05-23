package dev.gustavoquin.integrador.model.service;

import dev.gustavoquin.integrador.model.entity.Testimony;
import dev.gustavoquin.integrador.model.repository.TestimonyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestimonyService {
    @Autowired
    private TestimonyRepository repository;
    public List<Testimony> findAll() { return repository.findAll(); }
    public List<Testimony> findPending() { return repository.findByApprovedFalse(); }
    public List<Testimony> findApproved() { return repository.findByApprovedTrue(); }
    public Testimony findById(Long id) { return repository.findById(id).orElseThrow(); }
    public Testimony save(Testimony t) { return repository.save(t); }
    public void deleteById(Long id) { repository.deleteById(id); }
}
