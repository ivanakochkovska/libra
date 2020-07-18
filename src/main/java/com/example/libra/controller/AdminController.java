package com.example.libra.controller;

import com.example.libra.Model.*;
import com.example.libra.Repository.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:3000")

public class AdminController {
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    KnigaRepository knigaRepository;
    @Autowired
    RezervacijaRepository rezervacijaRepository;
    @Autowired
    PromocijaRepository promocijaRepository;
    @Autowired
    ChitalnaRepository chitalnaRepository;
    @Autowired
    KoktelZabavaRepository koktelZabavaRepository;
    /*
    @GetMapping
    public List<Korisnik>getUsers(){
        return korisnikRepository.findAll();
    }
    */


    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public ModelAndView login() {



        return new ModelAndView("redirect:http://localhost:3000");
    }



    @RequestMapping(value="/adminPanelSearch", method=RequestMethod.GET)
    public List<Korisnik>getAllUsers(){
        return korisnikRepository.findAll();

    }
    @RequestMapping(value="/adminPanelBooks", method = RequestMethod.GET)
    public List<Kniga> getAllBooks(){
        return (List<Kniga>) knigaRepository.findAll();
    }
    @RequestMapping(value="adminPanelSearchReservation", method=RequestMethod.GET)
    public List<Rezervacija>getAllReservations(){
        return rezervacijaRepository.findAll();
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateTemplate(HttpSession session)
    {
        session.setAttribute("admin","admin");
        return new ModelAndView("update");
    }


    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestParam(name = "sifra") String sifraKniga, Model model) {

            int flag = 0;
            List<Kniga> knigaList = (List<Kniga>) knigaRepository.findAll();
            Optional<Kniga> book1 = knigaRepository.findById(sifraKniga);
            Kniga b = book1.get();
            b.setKapacitet(b.updateBack());
            knigaRepository.save(b);



            return b.getIme();


    }

    @RequestMapping(value="/updateUserAdmin", method=RequestMethod.GET)
    public ModelAndView updateUserAdmin(@RequestParam(name="ime")String ime, @RequestParam(name="prezime")String prezime,
                                        @RequestParam(name="korisnickoime")String korisnickoime, @RequestParam(name="id")int id, @RequestParam(name="lozinka")String lozinka)
    {
        Korisnik korisnik=null;
        List<Korisnik> users=korisnikRepository.findAll();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId()==id){
                korisnik=users.get(i);
                korisnikRepository.delete(korisnik);
                break;
            }
        }
        System.out.println(ime);
        System.out.println(prezime);
        Korisnik newUser=new Korisnik(0,ime, prezime, korisnickoime, lozinka);
        korisnikRepository.save(newUser);
         return new ModelAndView("redirect:http://localhost:3000");
    }


    @RequestMapping(value="/updateReservationAdmin", method=RequestMethod.GET)
    public ModelAndView updateUserAdmin(@RequestParam(name="sifraRez")int sifraRez, @RequestParam(name="sifraKor")int sifraKor,
                                        @RequestParam(name="sifraKniga")String sifraKniga, @RequestParam(name="imeKniga")String imeKniga, @RequestParam(name="avtorKniga")String avtorKniga,
                                        @RequestParam(name="startt")String start, @RequestParam(name="endd")String end)
    {
        Rezervacija rezervacija=null;
        List<Rezervacija> rezervacijaList=rezervacijaRepository.findAll();
        for(int i=0;i<rezervacijaList.size();i++){
            if(rezervacijaList.get(i).getId()==sifraRez){
                rezervacija=rezervacijaList.get(i);
                rezervacijaRepository.delete(rezervacija);
                break;
            }
        }
        System.out.println(avtorKniga);

        Rezervacija newReservation=new Rezervacija(0,sifraKor,sifraKniga,imeKniga,avtorKniga,start,end);
        rezervacijaRepository.save(newReservation);

        return new ModelAndView("redirect:http://localhost:3000");
    }

    @RequestMapping(value="/updateBookAdmin", method=RequestMethod.GET)
    public ModelAndView updateUserAdmin(
                                        @RequestParam(name="sifraKniga")String sifraKniga, @RequestParam(name="imeKniga")String imeKniga, @RequestParam(name="avtorKniga")String avtorKniga,
                                        @RequestParam(name="kapacitet")int kapacitet, @RequestParam(name="opis")String opis, @RequestParam(name = "slika")String slika)
    {
        Kniga kniga=null;
        List<Kniga> knigaList= (List<Kniga>) knigaRepository.findAll();
        for(int i=0;i<knigaList.size();i++){
            if(knigaList.get(i).getId().equals(sifraKniga)){
                kniga=knigaList.get(i);
                knigaRepository.delete(kniga);
                break;
            }
        }

        Kniga newBook=new Kniga(sifraKniga,avtorKniga,imeKniga,opis,true,slika);
        newBook.setKapacitet(kapacitet);
        knigaRepository.save(newBook);

        return new ModelAndView("redirect:http://localhost:3000");
    }

    @RequestMapping(value = "/deliteBook", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deliteBook(@RequestParam(name = "sifra") String sifraKniga, HttpSession session, Model model) {
        if (session.getAttribute("admin").equals("admin")) {
            int flag = 0;
            List<Kniga> knigaList = (List<Kniga>) knigaRepository.findAll();
            Optional<Kniga> book1 = knigaRepository.findById(sifraKniga);
            Kniga b = book1.get();
            knigaRepository.delete(b);


            model.addAttribute("validD", true);
            return new ModelAndView("update");
        } else {
            model.addAttribute("invalidD", true);
            return new ModelAndView("update");
        }

    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addBook(@RequestParam(name="sifra") String sifra, @RequestParam(name="ime")String ime, @RequestParam(name="avtor")String avtor,@RequestParam(name="kapacitet")int kap,@RequestParam(name="opis")String opis, @RequestParam(name="addr")String addr, HttpSession session, Model model){
        Kniga kniga=new Kniga(sifra,avtor,ime,opis,true,addr);
        kniga.setKapacitet(kap);
        knigaRepository.save(kniga);
        model.addAttribute("validAdd", true);
        return new ModelAndView("update");

    }

    @RequestMapping(value="/addPromotion", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addPromotion(@RequestParam(name="avtor")String avtor, @RequestParam(name="kniga")String kniga
    ,@RequestParam(name="datum") String datum, @RequestParam(name="cas") String cas,Model model){
        Promocija promocija=new Promocija(0,avtor,kniga,datum,cas);
        promocijaRepository.save(promocija);
        model.addAttribute("validP", true);
        return new ModelAndView("update");

    }

    @RequestMapping(value="/delitePromotion", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView delitePromotion(@RequestParam(name="avtor")String avtor, @RequestParam(name="kniga")String kniga
            ,@RequestParam(name="datum") String datum, @RequestParam(name="cas") String cas, Model model){
        List<Promocija> promocijaList=promocijaRepository.findAll();
        Promocija promocija=null;
        for(int i=0;i<promocijaList.size();i++){
            if(promocijaList.get(i).getAvtor().equals(avtor)&&promocijaList.get(i).getKniga().equals(kniga)
            &&promocijaList.get(i).getChas().equals(cas)&&promocijaList.get(i).getDatum().equals(datum)
            ){
                promocija=promocijaList.get(i);
            }
        }
        promocijaRepository.delete(promocija);
        model.addAttribute("validDP", true);
        return new ModelAndView("update");

    }


    @RequestMapping(value="/addR", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addR(@RequestParam(name="datum")String datum, @RequestParam(name="vreme")String vreme
            , Model model){
        Chitalna chitalna=new Chitalna(0,datum,vreme);
        chitalnaRepository.save(chitalna);
        model.addAttribute("validR", true);
        return new ModelAndView("update");

    }


    @RequestMapping(value="/deliteR", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deliteR(@RequestParam(name="datum")String datum, @RequestParam(name="vreme")String vreme
            , Model model){
        Chitalna chitalna=null;
        List<Chitalna> chitalnaList=chitalnaRepository.findAll();
        for(int i=0;i<chitalnaList.size();i++){
            if(chitalnaList.get(i).getDatum().equals(datum)&&chitalnaList.get(i).getVremeZaCitanje().equals(vreme)){
                chitalna=chitalnaList.get(i);
            }
        }
        chitalnaRepository.delete(chitalna);
        model.addAttribute("validDR", true);
        return new ModelAndView("update");

    }


    @RequestMapping(value="/addZabava", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addZabava(@RequestParam(name="datum")String datum, @RequestParam(name="vreme")String vreme,
            @RequestParam(name="gostin")String gostin, Model model){

       KoktelZabava koktelZabava=new KoktelZabava(0,gostin,datum,vreme);
        model.addAttribute("validZ", true);
        return new ModelAndView("update");

    }


    @RequestMapping(value="/deliteZabava", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deliteZabava(@RequestParam(name="datum")String datum, @RequestParam(name="vreme")String vreme,
                                @RequestParam(name="gostin")String gostin, Model model){

        KoktelZabava koktelZabava=null;
        List<KoktelZabava> koktelZabavaList=koktelZabavaRepository.findAll();
        for(int i=0;i<koktelZabavaList.size();i++){
            if(koktelZabavaList.get(i).getChas().equals(vreme)&&koktelZabavaList.get(i).getDatum().equals(datum)
            &&koktelZabavaList.get(i).getImeNaSpecijalenGostina().equals(gostin)
            ){
                koktelZabava=koktelZabavaList.get(i);
            }

        }
        koktelZabavaRepository.delete(koktelZabava);
        model.addAttribute("validDZ", true);
        return new ModelAndView("update");

    }


@RequestMapping(value="adminPanelUpdateLogin", method = RequestMethod.POST)
@ResponseBody
    public ModelAndView adminUpdateLogin(@RequestParam(name="korisnickoime")String korisnickoime, @RequestParam(name="lozinka")String lozinka,HttpSession session)
{
    session.setAttribute("admin","admin");

        if(korisnickoime.equals("admin")&&lozinka.equals("admin"))
        return new ModelAndView("update");
        else
            return new ModelAndView("adminPanelUpdateLogin");

}

@RequestMapping(value="/admin", method = RequestMethod.GET)
    public ModelAndView admin(){
        return new ModelAndView("adminPanelUpdateLogin");
}



    @RequestMapping(value="/adminLogout", method = RequestMethod.GET)

    public ModelAndView logoutUser( HttpSession session){
        session.removeAttribute("admin");
        session.invalidate();
        return new ModelAndView("redirect:/");

    }

    @RequestMapping(value="/deleteBookAdmin", method=RequestMethod.GET)
    public ModelAndView deleteBookAdmin(@RequestParam(name="sifra") String sifra){
        List<Kniga> knigaList = (List<Kniga>) knigaRepository.findAll();
//        Optional<Kniga> book1 = knigaRepository.findById(sifra);
//        Kniga b = book1.get();
        Kniga b=null;
        for(int i=0;i<knigaList.size();i++){
            if(knigaList.get(i).getIme().equals(sifra)){
                b=knigaList.get(i);
                break;
            }
        }
        knigaRepository.delete(b);
        return new ModelAndView("redirect:http://localhost:3000");
        //return new ModelAndView("index");

    }


    @RequestMapping(value="/deleteUserAdmin", method=RequestMethod.GET)
    public ModelAndView deleteBookUser(@RequestParam(name="sifra") String sifra){
        List<Korisnik> korisniks = (List<Korisnik>) korisnikRepository.findAll();
//        Optional<Kniga> book1 = knigaRepository.findById(sifra);
//        Kniga b = book1.get();
        Korisnik b=null;
        for(int i=0;i<korisniks.size();i++){
            if(korisniks.get(i).getKorisnickoIme().equals(sifra)){
                b=korisniks.get(i);
                break;
            }
        }
        korisnikRepository.delete(b);
        return new ModelAndView("redirect:http://localhost:3000");
        //return new ModelAndView("index");

    }


    @RequestMapping(value="/deleteReservationAdmin", method=RequestMethod.GET)
    public ModelAndView deleteReservationUser(@RequestParam(name="sifra") int sifra){
        List<Rezervacija> korisniks = (List<Rezervacija>) rezervacijaRepository.findAll();
//        Optional<Kniga> book1 = knigaRepository.findById(sifra);
//        Kniga b = book1.get();
        Rezervacija b=null;
        for(int i=0;i<korisniks.size();i++){
            if(korisniks.get(i).getId_korisnik()==sifra){
                b=korisniks.get(i);
                break;
            }
        }
        rezervacijaRepository.delete(b);
        return new ModelAndView("redirect:http://localhost:3000");
        //return new ModelAndView("index");

    }




//    @CrossOrigin(origins="http://localhost:3001")
//    @GetMapping("/findall")
//
//    public List<Kniga> findAll() {
//
//        List<Kniga> customers = (List<Kniga>) knigaRepository.findAll();
//
//        List<Kniga> customerUI = new ArrayList<>();
//
//        for (Kniga customer : customers) {
//
//            customerUI.add(new Kniga(customer.getId(), customer.getAvtor(), customer.getIme(), customer.getKapacitet(), customer.getOpis(), customer.isStatus(), customer.getAdresa_slika()));
//
//
//        }
//
//
//        return customers;
//
//    }
//


/*
    @GetMapping(params = "username")
    public Optional<Korisnik> findOneUser(@RequestParam String username){
        List<Korisnik> korisnici= (List<Korisnik>) korisnikRepository.findAll();
        String userForSearch="";
        Long id=0L;
        for(int i=0;i<korisnici.size();i++){
            if(korisnici.get(i).getKorisnickoIme().equals(username)){
                userForSearch+=korisnici.get(i).getKorisnickoIme();
                id+=korisnici.get(i).getId();
                break;
            }

        }
        return korisnikRepository.findById(id);

    }
    */
}
