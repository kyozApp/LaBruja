package com.labrujastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labrujastore.entity.Atributos;

@Repository
public interface AtributosRepository extends JpaRepository<Atributos, Integer> {

}
