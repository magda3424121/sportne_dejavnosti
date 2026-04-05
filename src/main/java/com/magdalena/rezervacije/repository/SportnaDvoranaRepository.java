package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.SportnaDvorana;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SportnaDvoranaRepository extends CrudRepository<SportnaDvorana, Long> {
    List<SportnaDvorana> findByScId(Long scId);
    List<SportnaDvorana> findByImeContainingIgnoreCase(String ime);
}