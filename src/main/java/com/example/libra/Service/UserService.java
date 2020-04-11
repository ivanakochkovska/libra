package com.example.libra.Service;

import com.example.libra.Model.Kniga;
import com.example.libra.controller.KnigaRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public  class UserService  {

    @Autowired
    private KnigaRepository knigaRepository;
    @Transactional
    public List<Kniga> findAll(){
        var it = knigaRepository.findAll();
        var users=new ArrayList<Kniga>();
        it.forEach(e->users.add(e));
        return users;


    }


}
