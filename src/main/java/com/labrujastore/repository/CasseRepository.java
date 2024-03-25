package com.labrujastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labrujastore.entity.Casse;

@Repository
public interface CasseRepository extends JpaRepository<Casse, Integer>{

}
