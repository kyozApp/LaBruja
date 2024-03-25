package com.labrujastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labrujastore.entity.Refrigeracion;

@Repository
public interface RefrigeracionRepository extends JpaRepository<Refrigeracion, Integer>{

}
