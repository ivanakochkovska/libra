package com.example.libra.Service;

import com.example.libra.Model.Korisnik;
import com.example.libra.Repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class KorisnikService  {

    @Autowired
    KorisnikRepository korisnikRepository;
    @Transactional
    public Korisnik save(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }

}
