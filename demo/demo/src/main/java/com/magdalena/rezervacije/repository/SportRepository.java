package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.Sport;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SportRepository extends CrudRepository<Sport, Long> {
    List<Sport> findBySdId(Long sdId);
    List<Sport> findByImeContainingIgnoreCase(String ime);
}