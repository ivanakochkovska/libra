package com.example.libra.Model;

import com.example.libra.Model.Kniga;
import lombok.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table
@Data

@RequiredArgsConstructor


@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public final long id;
    public  final String ime;
    public  final String prezime;
    public final String korisnickoIme;

    public final String lozinka;








}
