package com.example.libra.Model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Entity
@Data
@Table
public class Chitalna {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;

   private final  String datum;
   private final String vremeZaCitanje;



}

