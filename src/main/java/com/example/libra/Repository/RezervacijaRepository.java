package com.example.libra.Repository;

import com.example.libra.Model.Rezervacija;
import org.apache.catalina.startup.CredentialHandlerRuleSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

}
