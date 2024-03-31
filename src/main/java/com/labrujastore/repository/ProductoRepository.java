package com.labrujastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labrujastore.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}
