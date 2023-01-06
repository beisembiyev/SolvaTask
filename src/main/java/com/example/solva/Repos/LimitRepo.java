package com.example.solva.Repos;

import com.example.solva.Models.Entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimitRepo extends JpaRepository<Limit, Long> {
    Limit findLimitById(long id);
}
