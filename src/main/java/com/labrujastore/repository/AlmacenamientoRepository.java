package com.labrujastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labrujastore.entity.Almacenamiento;

@Repository
public interface AlmacenamientoRepository extends JpaRepository<Almacenamiento, Integer>{

}
