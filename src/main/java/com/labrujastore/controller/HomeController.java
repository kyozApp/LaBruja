package com.labrujastore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.labrujastore.entity.Banner;
import com.labrujastore.entity.Combo;
import com.labrujastore.entity.Marca;
import com.labrujastore.service.BannerService;
import com.labrujastore.service.ComboService;
import com.labrujastore.service.MarcaService;

@Controller
public class HomeController {
	@Autowired
	private BannerService bannerService;
	@Autowired
	private MarcaService marcaService;
	@Autowired
	private ComboService comboService;

	@GetMapping("/")
	public String index(Model model) {
		List<Marca> marcas = marcaService.listarMarca();
		List<Combo> combos = comboService.listarCombo();
		Banner bannerId1 = bannerService.obtenerIdBanner(1);
		Banner bannerId2 = bannerService.obtenerIdBanner(2);
		Banner bannerId3 = bannerService.obtenerIdBanner(3);
		Banner bannerId4 = bannerService.obtenerIdBanner(4);
		Banner bannerId5 = bannerService.obtenerIdBanner(5);
		model.addAttribute("vistaMarcas", marcas);
		model.addAttribute("vistaCombos", combos);
		model.addAttribute("bannerId1", bannerId1);
		model.addAttribute("bannerId2", bannerId2);
		model.addAttribute("bannerId3", bannerId3);
		model.addAttribute("bannerId4", bannerId4);
		model.addAttribute("bannerId5", bannerId5);
		return "index";
	}
}
