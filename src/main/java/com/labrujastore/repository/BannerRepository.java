package com.labrujastore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labrujastore.entity.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
    List<Banner> findByTipo(String tipo);
}
