package com.example.libra.Repository;

import com.example.libra.Model.Chitalna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ChitalnaRepository extends JpaRepository<Chitalna, Long> {
}
