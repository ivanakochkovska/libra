package com.example.libra.controller;

import com.example.libra.Model.Kniga;
import com.example.libra.Model.Korisnik;
import com.example.libra.Model.Rezervacija;
import com.example.libra.Repository.KorisnikRepository;
import com.example.libra.Repository.RezervacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ProfilController {

    @Autowired
    KnigaRepository knigaRepository;
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    RezervacijaRepository rezervacijaRepository;

    /*
    @GetMapping("/najava")
    public String getProfilePage(){
        return "profil";
    }*/

    @GetMapping("/profilAllBooks")
    public ModelAndView getProfilePae(@PageableDefault(size = 3) Pageable pageable, Model model) {
        Page<Kniga> page = knigaRepository.findAll(pageable);
        List<Kniga> knigi = (List<Kniga>) knigaRepository.findAll();
        model.addAttribute("page", page);
        //model.addAttribute("knigi", knigi);


        return new ModelAndView("siteKnigi");
    }


    @GetMapping("/profil")
    public ModelAndView getPr() {
        return new ModelAndView("profil1");
    }


    @GetMapping("/uspesna")
    public ModelAndView testiranje() {
        return new ModelAndView("uspesna");
    }


    @RequestMapping(value = "/profil", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView makeReservration(@RequestParam(name = "ime") String ime,
                                         @RequestParam(name = "avtor") String avtor,
                                         @RequestParam(name = "sifra") String sifra,
                                         HttpSession session, Model model) {
        if (knigaRepository.findById(sifra).isPresent() == false) {

            model.addAttribute("invalid", true);
            return new ModelAndView("profil1");
        }


        String username = (String) session.getAttribute("username");
        List<Kniga> knigi = (List<Kniga>) knigaRepository.findAll();
        Kniga book = null;
        for (int i = 0; i < knigi.size(); i++) {
            if (knigi.get(i).getId().equals(sifra)) {
                book = knigi.get(i);
            }

        }

        System.out.println(book.getKapacitet());
        int cap = book.getKapacitet();

        if (cap == 0) {

            model.addAttribute("nepostoecka", true);
            return new ModelAndView("profil1");
        } else {
            book.setKapacitet(book.updatePlus());
        }


        int flag = 0;
        List<Korisnik> korisniks = korisnikRepository.findAll();
        for (int i = 0; i < korisniks.size(); i++) {
            if (korisniks.get(i).getKorisnickoIme().equals(username)) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar from = Calendar.getInstance();
                String od = sdf.format(from.getTime());
                from.add(Calendar.DAY_OF_MONTH, 10);
                String to = sdf.format(from.getTime());
                rezervacijaRepository.save(new Rezervacija(0, korisniks.get(i).getId(), sifra, ime, avtor, od, to));
                //return new ModelAndView("redirect:/index");

                flag += 1;
                break;
            } else {

                continue;
            }
        }


        if (flag != 0) {
            model.addAttribute("zajmuvanje", true);
            return new ModelAndView("profil1");

        } else {
            model.addAttribute("invalid", true);
            return new ModelAndView("profil1");


        }


    }

    @RequestMapping(value = "/kniga", method = RequestMethod.POST)
    @ResponseBody

    public ModelAndView posebnaKniga(@RequestParam(name = "pole") String pole, Model model) {
        List<Kniga> knigaLista = (List<Kniga>) knigaRepository.findAll();

        List<Kniga> findedBooks = new ArrayList<>();
        for (int i = 0; i < knigaLista.size(); i++) {
            if (knigaLista.get(i).getIme().equals(pole) || knigaLista.get(i).getAvtor().equals(pole)) {

                findedBooks.add(knigaLista.get(i));


            }

        }
        model.addAttribute("oneBook", findedBooks);
        return new ModelAndView("ednaKnigaPregled");
    }

    @RequestMapping(value = "/profilSostojba", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView istorijaNaKnigi(HttpSession session, Model model) {
        String korisnickoIme = (String) session.getAttribute("username");
        List<Kniga> knigi = (List<Kniga>) knigaRepository.findAll();
        model.addAttribute("knigi", knigi);
        List<Rezervacija> rezervacii = rezervacijaRepository.findAll();
        List<Korisnik> korisnici = korisnikRepository.findAll();
        int flag = 0;
        long id = 0;
        for (int i = 0; i < korisnici.size(); i++) {
            if (korisnici.get(i).getKorisnickoIme().equals(korisnickoIme)) {
                flag += 1;
                id += korisnici.get(i).getId();


            }
        }
        int idRez = 0;

        List<Rezervacija> rezervacijaList = new ArrayList<>();
        if (flag != 0) {
            for (int i = 0; i < rezervacii.size(); i++) {


                if (rezervacii.get(i).getId_korisnik() == id) {

                    rezervacijaList.add(rezervacii.get(i));


                } else {
                    continue;
                }
            }

            model.addAttribute("rezervacii", rezervacijaList);
            return new ModelAndView("profil1");
        } else {
            model.addAttribute("invalid", true);
            return new ModelAndView("profil1");
        }

    }



}


