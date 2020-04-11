package com.example.libra.controller;

import com.example.libra.Model.Chitalna;
import com.example.libra.Model.Kniga;
import com.example.libra.Model.KoktelZabava;
import com.example.libra.Model.Promocija;
import com.example.libra.Repository.ChitalnaRepository;
import com.example.libra.Repository.KoktelZabavaRepository;
import com.example.libra.Repository.PromocijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    KnigaRepository knigaRepository;

    @Autowired
    PromocijaRepository promocijaRepository;
    @Autowired
    ChitalnaRepository chitalnaRepository;
    @Autowired
    KoktelZabavaRepository koktelZabavaRepository;


/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getLoginForm1(Model model) {
        List<Kniga> knigi = (List<Kniga>) knigaRepository.findAll();
        model.addAttribute("knigi", knigi);
        return new ModelAndView("index");
    }
    */

    @GetMapping ("/")
    public ModelAndView knigi(Model model) {

        List<Kniga> knigi = (List<Kniga>) knigaRepository.findAll();
        model.addAttribute("knigi", knigi);

        List<Kniga> knigiNova=(List<Kniga>) knigaRepository.findAll();

        List<Kniga> najnoviKnigi=new ArrayList<>();

        List<Promocija> promocijaList=promocijaRepository.findAll();
        model.addAttribute("promocii", promocijaList);
        List<Chitalna> chitalnaList=chitalnaRepository.findAll();
        model.addAttribute("chitalni", chitalnaList);
        List<KoktelZabava> koktelZabavaList=koktelZabavaRepository.findAll();
        model.addAttribute("zabavi", koktelZabavaList);

        int counter=0;
            int m=knigiNova.size()-5;
        for(int i=knigiNova.size()-1;i>m;i--){
            najnoviKnigi.add(knigiNova.get(i));
            counter++;
            if(counter==4)
                break;
        }


        model.addAttribute("najnoviKnigi", najnoviKnigi);
        return new ModelAndView("index");
    }


/*
    @PostMapping("/create")
    public String create(@RequestBody Kniga kniga) {
        knigaRepository.save(new Kniga(kniga.getId(), kniga.getAvtor(), kniga.getIme(), kniga.getKapacitet(), kniga.getOpis(), kniga.isStatus(), kniga.getAdresa_slika()));

        return "customer is created";
    }

*/


/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView najnoviKnigiFilter(Model model)
    {
        List<Kniga> knigi=(List<Kniga>) knigaRepository.findAll();

        List<Kniga> najnoviKnigi=new ArrayList<>();
        //model.addAttribute("najnoviKnigi", najnoviKnigi);
        int counter=0;

        for(int i=knigi.size();i>knigi.size()-4;i--){
            najnoviKnigi.add(knigi.get(i));
        }
        model.addAttribute("najnoviKnigi", najnoviKnigi);

        return new ModelAndView("index");
    }
    */


    @RequestMapping(value = "/allbooks", method = RequestMethod.GET)
    public ModelAndView getAllBooks(Model model) {
        List<Kniga> knigi = (List<Kniga>) knigaRepository.findAll();
        model.addAttribute("knigi", knigi);
        return new ModelAndView("nov");
    }









}
