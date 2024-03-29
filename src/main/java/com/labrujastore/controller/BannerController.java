package com.labrujastore.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.labrujastore.entity.Banner;
import com.labrujastore.service.BannerService;

@Controller
@RequestMapping("/admin")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/banner")
    public String index(Model model) {
        List<Banner> banners = bannerService.listaBanner();
        model.addAttribute("tablaBanner", banners);
        return "admin/banner/index";
    }

    @GetMapping("/banner/crear")
    public String crear(Model model) {
        Banner banner = new Banner();
        model.addAttribute("formularioCrearBanner", banner);
        return "admin/banner/crear";
    }

    @PostMapping("/banner/crear")
    public String crear(@ModelAttribute Banner banner,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        banner.setImagenNombre(imagen.getOriginalFilename());
        banner.setImagenArchivo(imagen.getBytes());
        bannerService.guardarBanner(banner);
        return "redirect:/admin/banner";
    }

    @GetMapping("/banner/editar/{bannerId}")
    public String editar(@PathVariable Integer bannerId, Model model) {
        Banner banner = bannerService.obtenerIdBanner(bannerId);
        model.addAttribute("banner", banner);
        return "admin/banner/editar";
    }

    @PostMapping("/banner/editar/{bannerId}")
    public String editar(@PathVariable Integer bannerId, @ModelAttribute Banner banner,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        Banner bannerExistente = bannerService.obtenerIdBanner(bannerId);
        bannerExistente.setNombre(banner.getNombre());
        if (!imagen.isEmpty()) {
            bannerExistente.setImagenNombre(imagen.getOriginalFilename());
            bannerExistente.setImagenArchivo(imagen.getBytes());
        }
        bannerService.guardarBanner(bannerExistente);
        return "redirect:/admin/banner";
    }

    @GetMapping("/banner/{bannerId}")
    public String eliminar(@PathVariable Integer bannerId) {
        bannerService.eliminarBanner(bannerId);
        return "redirect:/admin/banner";
    }
}
