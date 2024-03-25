package com.labrujastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labrujastore.entity.Ram;

@Repository
public interface RamRepository extends JpaRepository<Ram, Integer>{

}
