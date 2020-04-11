package com.example.libra.controller;

import com.example.libra.Model.*;
import com.example.libra.Repository.ChitalnaRepository;
import com.example.libra.Repository.KoktelZabavaRepository;
import com.example.libra.Repository.KorisnikRepository;
import com.example.libra.Repository.PromocijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddEvents {


    @Autowired
    KnigaRepository knigaRepository;
    @Autowired
    PromocijaRepository promocijaRepository;
    @Autowired
    ChitalnaRepository chitalnaRepository;
    @Autowired
    KoktelZabavaRepository koktelZabavaRepository;
    @Autowired
    KorisnikRepository korisnikRepository;
    @GetMapping("/addEvents")
    public String addEvents(){
        int cap=0;
        Kniga kniga =new Kniga("978-608-260-036-9", "Емили Гунис", "Девојката од писмото", "Саманта Харпер е новинарка очајна за малку одмор. Кога случајно налетува на писмо од минатото, истото ја шокира и трогнува. Писмото е од млада мајка која моли да ја спасат од Света Маргарита. Пред да биде предоцна.", true, "1.jpg");
        knigaRepository.save(new Kniga(kniga.getId(), kniga.getAvtor(), kniga.getIme(), kniga.getOpis(), kniga.isStatus(), kniga.getAdresa_slika()));
        //kniga.setCapacity(0);
        kniga.setKapacitet(0);
        Kniga kniga1=new Kniga("978-608-260-003-1", "Џон Грин", "Желки до бесконечност",  "Шеснаесетгодишната Аза нема желба и намера да изигрува детектив и да разоткрива мистерија за милијардер во бегство, но сепак има награда од сто илјади долари во игра и нејзината најдобра и најбестрашна пријателка, Дејзи, е желна за истражување.", true, "2.jpg");
        kniga1.setKapacitet(20);
        knigaRepository.save(kniga1);

       Kniga kniga2=new Kniga("978-608-4672-92-0", "Сара Џио", "Темјанушки во март",  "Во дваесеттите години, Емили Вилсон е на врвот на светот: таа е авторка на Њујорк Тајмс бестселер, има сопруг што изгледа како изваден од некоја насловна страница и сигурна е дека ја очекува само среќа до крајот на животот.", true, "3.jpg");
       kniga2.setKapacitet(10);
       knigaRepository.save(kniga2);
        //kniga2.setCapacity(20);

        Kniga kniga3=new Kniga("978-608-4672-90-6", "Б.А. Парис", "На раб на лудило",  "Ноќ, сред страшна бура, Кес се враќа дома сама и сопругот ја замолува да не крати преку патот во шумата, бидејќи тој пат е опасен кога дивее страшна бура. Но и покрај тоа што му ветува дека нема, таа не сака да патува 40 минути, па пресекува низ шумата.", true, "4.jpg");
        kniga3.setKapacitet(50);
        knigaRepository.save(kniga3);
        //kniga3.setCapacity(20);
        Kniga kniga4=new Kniga("978-608-4672-13-5", "Елизабет Хејнс", "Скриена во најмрачниот агол","Прекрасниот, харизматичен и спонтан Ли изгледа премногу совршен за да биде реален. Пријателките на Кетрин очигледно се согласуваат, бидејќи секоја една од нив паѓа на неговиот шарм.", true, "5.jpg");
        kniga4.setKapacitet(30);
        knigaRepository.save(kniga4);
       // kniga4.setCapacity(20);
        Kniga kniga5=new Kniga("978-608-260-021-5", "Маја Божиновска", "Флерт, уцени и високи потпетици",  "Викторија е експерт за одење на интервјуа и рандевуа. Таа е малку несмасна, но крајно симпатична хероина која е во постојана потрага по совршената работа и совршениот маж. Еден ден, нејзиниот досаден живот добива сосема друга димензија...", true, "6.jpg");
        kniga5.setKapacitet(10);
        knigaRepository.save(kniga5);
        //kniga5.setCapacity(20);
        Kniga kniga6=new Kniga("978-608-260-011-6", "Колин Хувер", "Сите твои совршености", "На совршената љубов на Квин и Грахам ѝ се заканува нивниот несовршен брак. Спомените, грешките и тајните што се трупале низ годините сега ги влечат на различни страни и уште повеќе ја зголемуваат дистанцата меѓу нив. ", true, "7.jpg");
        kniga6.setKapacitet(50);
        knigaRepository.save(kniga6);
        //kniga6.setCapacity(30);
        Kniga kniga7=new Kniga("978-608-260-015-4", "Колин Хувер", "Верити",  "Ловен е авторка на раб на финансиска катастрофа кога прифаќа една од оние понуди што доаѓаат еднаш во животот. Џереми Крафорд, сопруг на легендарната авторка Верити Крафорд, ја ангажира Ловен да ги доврши останатите книги...", true, "8.jpg");
        kniga7.setKapacitet(30);
        knigaRepository.save(kniga7);
        //kniga7.setCapacity(35);
        Promocija promocija=new Promocija(0, "МАЈА БОЖИНОВСКА", "ФЛЕРТ, УЦЕНИ И ВИСОКИ ПОТПЕТИЦИ", "20.04.2020", "14:00-16:00");
        promocijaRepository.save(new Promocija(promocija.getId(), promocija.getAvtor(), promocija.getKniga(), promocija.getDatum(), promocija.getChas()));

        Promocija promocija1=new Promocija(0, "МАЈА БОЖИНОВСКА", "ФЛЕРТ, УЦЕНИ И ВИСОКИ ПОТПЕТИЦИ", "27.04.2020", "14:00-16:00");
        promocijaRepository.save(new Promocija(promocija1.getId(), promocija1.getAvtor(), promocija1.getKniga(), promocija1.getDatum(), promocija1.getChas()));

        Chitalna chitalna=new Chitalna(0, "15.03.2020", "08:00");
        chitalnaRepository.save(new Chitalna(chitalna.getId(), chitalna.getDatum(), chitalna.getVremeZaCitanje()));

        KoktelZabava koktelZabava=new KoktelZabava(0,"Давор Стојановски", "14.03.2020","20:00");
        koktelZabavaRepository.save(new KoktelZabava(koktelZabava.getId(), koktelZabava.getImeNaSpecijalenGostina(), koktelZabava.getDatum(), koktelZabava.getChas()));
        Korisnik admin=new Korisnik(0,"admin", "admin","admin","admin");
        korisnikRepository.save(new Korisnik(admin.getId(),admin.getIme(),admin.getPrezime(),admin.getKorisnickoIme(),admin.getLozinka()));


        return "index";



    }






}
