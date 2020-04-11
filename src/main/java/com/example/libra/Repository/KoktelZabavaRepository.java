package com.example.libra.Repository;

import com.example.libra.Model.KoktelZabava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoktelZabavaRepository extends JpaRepository<KoktelZabava, Long> {
}
