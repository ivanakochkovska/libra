package com.example.libra.controller;

import com.example.libra.Model.Kniga;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface KnigaRepository extends PagingAndSortingRepository<Kniga, String> {



}


