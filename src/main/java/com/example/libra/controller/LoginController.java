package com.example.libra.controller;

import com.example.libra.Model.Kniga;
import com.example.libra.Model.Korisnik;
import com.example.libra.Repository.KorisnikRepository;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

@Controller

public class LoginController {

    @Autowired
    KorisnikRepository korisnikRepository;
    
    

    @GetMapping("/register")
    public String getLoginForm() {
        return "register";
    }

    @GetMapping ("/login")
    public String getLoginForm2() {
        return "loginn";
    }
/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getLoginForm1(Model model) {
        List<Kniga> knigi = (List<Kniga>) knigaRepository.findAll();
        model.addAttribute("knigi", knigi);
        return "index";
    }
    */
/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getLoginForm1(Model model) {
        List<Kniga> knigi = (List<Kniga>) knigaRepository.findAll();
        model.addAttribute("knigi", knigi);
        return "index";
    }
    */



    @Autowired
    KnigaRepository knigaRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public List<Kniga> getT() {

        List<Kniga> knigi = (List<Kniga>) knigaRepository.findAll();
        return knigi;
    }



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView userSubmit(@RequestParam(name = "ime") String ime,
                                   @RequestParam(name = "prezime") String prezime,
                                   @RequestParam(name = "korisnickoIme") String korisnickoIme,
                                   @RequestParam(name = "lozinka") String lozinka, Model model) {

        List<Korisnik> listaKorisnik=korisnikRepository.findAll();

            if(listaKorisnik.size()!=0) {
                for (int i = 0; i < listaKorisnik.size(); i++) {
                    if (listaKorisnik.get(i).getKorisnickoIme().equals(korisnickoIme)) {
                        model.addAttribute("invalid", true);
                        return new ModelAndView("register");


                    } else {
                        korisnikRepository.save(new Korisnik(0, ime, prezime, korisnickoIme, lozinka));
                    }
                }
            }
        else{
                    korisnikRepository.save(new Korisnik(0, ime, prezime, korisnickoIme, lozinka));
                }



        return new ModelAndView("redirect:/login");


    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView userLogin(@RequestParam(name = "korisnickoIme") String korisnickoIme,
                                  @RequestParam(name = "lozinka") String lozinka,
                                  HttpSession session, Model model,HttpServletResponse response) throws IOException {

        if(korisnickoIme.equals("admin")&&lozinka.equals("admin")){
            response.sendRedirect("http://localhost:3000");

        }
    else {
            List<Korisnik> korisnici = korisnikRepository.findAll();
            int flag = 0;
            for (int i = 0; i < korisnici.size(); i++) {
                Korisnik user = korisnici.get(i);
                if (user.getKorisnickoIme().equals(korisnickoIme) && user.getLozinka().equals(lozinka)) {
                    session.setAttribute("username", korisnickoIme);
                    flag += 1;
                    break;


                } else {
                /*
                model.addAttribute("invalid", true);

                    return new ModelAndView("loginn");
                    */
                    continue;
                }
            }
            if (flag != 0) {

                return new ModelAndView("redirect:/profil");
            } else {
                model.addAttribute("invalid", true);
                return new ModelAndView("loginn");
            }


        }
    return null;




    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)

    public ModelAndView logoutUser( HttpSession session){
        session.removeAttribute("username");
        session.invalidate();
        return new ModelAndView("redirect:/");

    }



}






