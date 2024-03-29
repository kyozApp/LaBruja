package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Banner;

public interface BannerService {
    public List<Banner> listaBanner();

    public Banner guardarBanner(Banner banner);

    public Banner actualizarBanner(Banner banner);

    public Banner obtenerIdBanner(Integer bannerId);

    public void eliminarBanner(Integer bannerId);
}
