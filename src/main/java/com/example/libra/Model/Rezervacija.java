package com.example.libra.Model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;



@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Entity
@Data
@Table
public class Rezervacija {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private final long  id;

    //@Column(name="id-korisnik")
    private final long id_korisnik;

    //@Column(name="id-kniga")
    private final String id_kniga;

    private  final String imeKniga;

    private final String avtor;

    private final String pocetenDatum;

    private final String kraenDatum;




}
