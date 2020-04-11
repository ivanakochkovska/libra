package com.example.libra.Model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Entity
@Data
@Table
public class Kniga {

    @Id

    private final String id;

    private final String avtor;
    private final String ime;
    public  int kapacitet;
    private final String opis;
    private final boolean status;

    private final String adresa_slika;

//    //int capacity;
//    public void setCapacity(int c){
//        this.capacity=c;
//    }
    public int updatePlus(){

        kapacitet-=1;
        return kapacitet;
    }

    public int updateBack(){
        kapacitet+=1;

        return kapacitet;
    }





//    public int getCapacity(){
//        return this.capacity;
//    }



}