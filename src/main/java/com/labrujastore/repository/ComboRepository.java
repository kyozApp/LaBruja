package com.labrujastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labrujastore.entity.Combo;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Integer> {

}
