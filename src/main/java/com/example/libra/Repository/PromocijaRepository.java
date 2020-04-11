package com.example.libra.Repository;

import com.example.libra.Model.Promocija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocijaRepository extends JpaRepository<Promocija, Long> {
}
