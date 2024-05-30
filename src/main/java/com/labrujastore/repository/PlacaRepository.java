package com.labrujastore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labrujastore.entity.Placa;

@Repository
public interface PlacaRepository extends JpaRepository<Placa, Integer>
{
    List<Placa> findByItemsProcesador_ProcesadorId(Integer procesadorId);
}
