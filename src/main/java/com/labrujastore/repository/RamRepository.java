package com.labrujastore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labrujastore.entity.Ram;

@Repository
public interface RamRepository extends JpaRepository<Ram, Integer>
{
    List<Ram> findByItemsPlaca_PlacaId(Integer placaId);
}
