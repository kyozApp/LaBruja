package com.labrujastore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.labrujastore.entity.Accesorio;
import com.labrujastore.entity.Almacenamiento;
import com.labrujastore.entity.Banner;
import com.labrujastore.entity.Casse;
import com.labrujastore.entity.Combo;
import com.labrujastore.entity.Fuente;
import com.labrujastore.entity.Laptop;
import com.labrujastore.entity.Marca;
import com.labrujastore.entity.Monitor;
import com.labrujastore.entity.Placa;
import com.labrujastore.entity.Procesador;
import com.labrujastore.entity.Ram;
import com.labrujastore.entity.Refrigeracion;
import com.labrujastore.entity.Tarjeta;
import com.labrujastore.service.AccesorioService;
import com.labrujastore.service.AlmacenamientoService;
import com.labrujastore.service.BannerService;
import com.labrujastore.service.CasseService;
import com.labrujastore.service.ComboService;
import com.labrujastore.service.FuenteService;
import com.labrujastore.service.LaptopService;
import com.labrujastore.service.MarcaService;
import com.labrujastore.service.MonitorService;
import com.labrujastore.service.PlacaService;
import com.labrujastore.service.ProcesadorService;
import com.labrujastore.service.RamService;
import com.labrujastore.service.RefrigeracionService;
import com.labrujastore.service.TarjetaService;

@Controller
public class HomeController {
	@Autowired
	private BannerService bannerService;

	@Autowired
	private MarcaService marcaService;

	@Autowired
	private ComboService comboService;

	@Autowired
	private AccesorioService accesorioService;

	@Autowired
	private AlmacenamientoService almacenamientoService;

	@Autowired
	private CasseService casseService;

	@Autowired
	private FuenteService fuenteService;

	@Autowired
	private LaptopService laptopService;

	@Autowired
	private MonitorService monitorService;

	@Autowired
	private PlacaService placaService;

	@Autowired
	private ProcesadorService procesadorService;

	@Autowired
	private RamService ramService;

	@Autowired
	private RefrigeracionService refrigeracionService;

	@Autowired
	private TarjetaService tarjetaService;

	@GetMapping("/")
	public String index(Model model) {

		List<Marca> marcas = marcaService.listarMarca();
		List<Combo> combos = comboService.listarCombo();

		Banner bannerId1 = bannerService.obtenerIdBanner(1);
		Banner bannerId2 = bannerService.obtenerIdBanner(2);
		Banner bannerId3 = bannerService.obtenerIdBanner(3);
		Banner bannerId4 = bannerService.obtenerIdBanner(4);
		Banner bannerId5 = bannerService.obtenerIdBanner(5);

		List<Accesorio> accesorios = accesorioService.listarAccesorio();
		List<Almacenamiento> almacenamientos = almacenamientoService.listarAlmacenamiento();
		List<Casse> casses = casseService.listarCasse();
		List<Fuente> fuentes = fuenteService.listarFuente();
		List<Laptop> laptops = laptopService.listarLaptop();
		List<Monitor> monitores = monitorService.listarMonitor();
		List<Placa> placas = placaService.listarPlaca();
		List<Procesador> procesadores = procesadorService.listarProcesador();
		List<Ram> rams = ramService.listarRam();
		List<Refrigeracion> refrigeraciones = refrigeracionService.listarRefrigeracion();
		List<Tarjeta> tarjetas = tarjetaService.listarTarjeta();

		model.addAttribute("vistaMarcas", marcas);
		model.addAttribute("vistaCombos", combos);

		model.addAttribute("bannerId1", bannerId1);
		model.addAttribute("bannerId2", bannerId2);
		model.addAttribute("bannerId3", bannerId3);
		model.addAttribute("bannerId4", bannerId4);
		model.addAttribute("bannerId5", bannerId5);

		model.addAttribute("vistaAccesorios", accesorios);
		model.addAttribute("vistaAlmacenamientos", almacenamientos);
		model.addAttribute("vistaCasses", casses);
		model.addAttribute("vistaFuentes", fuentes);
		model.addAttribute("vistaLaptops", laptops);
		model.addAttribute("vistaMonitores", monitores);
		model.addAttribute("vistaPlacas", placas);
		model.addAttribute("vistaProcesadores", procesadores);
		model.addAttribute("vistaRams", rams);
		model.addAttribute("vistaRefrigeraciones", refrigeraciones);
		model.addAttribute("vistaTarjetas", tarjetas);

		return "index";
	}

	@GetMapping("/detalle/producto/{id}")
	public String detalles(Model model, @PathVariable Integer id, @RequestParam("tipo") String tipo) {
		if ("accesorio".equals(tipo)) {
			Accesorio accesorio = accesorioService.obtenerIdAccesorio(id);
			model.addAttribute("accesorio", accesorio);
		} else if ("almacenamiento".equals(tipo)) {
			Almacenamiento almacenamiento = almacenamientoService.obtenerIdAlmacenamiento(id);
			model.addAttribute("almacenamiento", almacenamiento);
		} else if ("casse".equals(tipo)) {
			Casse casse = casseService.obtenerIdCasse(id);
			model.addAttribute("casse", casse);
		}
		return "producto-detalle/prueba";
	}

}
