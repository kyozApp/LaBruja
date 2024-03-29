package com.labrujastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Banner;
import com.labrujastore.repository.BannerRepository;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @SuppressWarnings("null")
    @Override
    public Banner actualizarBanner(Banner banner) {
        return bannerRepository.save(banner);
    }

    @SuppressWarnings("null")
    @Override
    public void eliminarBanner(Integer bannerId) {
        bannerRepository.deleteById(bannerId);
    }

    @SuppressWarnings("null")
    @Override
    public Banner guardarBanner(Banner banner) {
        return bannerRepository.save(banner);
    }

    @Override
    public List<Banner> listaBanner() {
        return bannerRepository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public Banner obtenerIdBanner(Integer bannerId) {
        return bannerRepository.findById(bannerId).get();
    }

}
