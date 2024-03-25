package com.labrujastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labrujastore.entity.Accesorio;

@Repository
public interface AccesorioRepository extends JpaRepository<Accesorio, Integer>{

}
