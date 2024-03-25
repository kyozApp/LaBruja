package com.labrujastore.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.labrujastore.entity.Categoria;
import com.labrujastore.entity.Tarjeta;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.TarjetaService;

@Controller
@RequestMapping("/admin")
public class TarjetaController {

    private static String storageLocation = "src/main/resources/static/admin/tarjeta";

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/tarjeta")
    public String index(Model model) {
        List<Tarjeta> tarjetas = tarjetaService.listarTarjeta();
        for (Tarjeta tarjeta : tarjetas) {
            tarjeta.setImagenArchivo("http://localhost:8080//admin/tarjeta/" + tarjeta.getImagenNombre());
        }
        model.addAttribute("tablaTarjeta", tarjetas);
        return "admin/tarjeta/index";
    }

    @GetMapping("/tarjeta/crear")
    public String crear(Model model) {
        Tarjeta tarjeta = new Tarjeta();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearTarjeta", tarjeta);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/tarjeta/crear";
    }

    @PostMapping("/tarjeta/crear")
    public String crear(@ModelAttribute("formularioCrearTarjeta") Tarjeta tarjeta,
                        @RequestPart("imagen") MultipartFile imagen) {
        try {
            // Generar un nombre único para el archivo de imagen
            String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

            // Establecer el nombre de la imagen en la tarjeta
            tarjeta.setImagenNombre(imagenNombre);

            // Obtener la ruta del archivo donde se almacenará la imagen
            Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

            // Copiar los bytes de la imagen al archivo en el sistema de archivos
            Files.copy(imagen.getInputStream(), filePath);

            // Construir la URL de la imagen
            String imageUrl = "http://localhost:8080//admin/tarjeta/" + imagenNombre;

            // Establecer la URL de la imagen en la tarjeta
            tarjeta.setImagenArchivo(imageUrl);

            // Guardar la tarjeta en la base de datos
            tarjetaService.guardarTarjeta(tarjeta);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/tarjeta";
    }

    @GetMapping("/tarjeta/editar/{tarjetaId}")
    public String editar(@PathVariable Integer tarjetaId, Model model) {
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioEditarTarjeta", tarjetaService.obtenerIdTarjeta(tarjetaId));
        model.addAttribute("selectorCategorias", categorias);
        return "admin/tarjeta/editar";
    }

    @PostMapping("/tarjeta/editar/{tarjetaId}")
    public String editar(@PathVariable Integer tarjetaId,
                         @ModelAttribute("formularioEditarTarjeta") Tarjeta tarjeta,
                         @RequestPart("imagen") MultipartFile imagen) {
        try {
            Tarjeta tarjetaAnterior = tarjetaService.obtenerIdTarjeta(tarjetaId);

            // Verificar si se ha proporcionado una nueva imagen
            if (!imagen.isEmpty()) {
                // Eliminar la imagen anterior
                eliminarImagen(tarjetaAnterior.getImagenNombre());

                // Generar un nombre único para el archivo de imagen
                String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

                // Establecer el nombre de la nueva imagen en la tarjeta
                tarjeta.setImagenNombre(imagenNombre);

                // Obtener la ruta del archivo donde se almacenará la nueva imagen
                Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

                // Copiar los bytes de la imagen al archivo en el sistema de archivos
                Files.copy(imagen.getInputStream(), filePath);

                // Construir la URL de la nueva imagen
                String imageUrl = "http://localhost:8080//admin/tarjeta/" + imagenNombre;

                // Establecer la URL de la nueva imagen en la tarjeta
                tarjeta.setImagenArchivo(imageUrl);
            } else {
                // Si no se proporciona una nueva imagen, mantener la imagen anterior
                tarjeta.setImagenNombre(tarjetaAnterior.getImagenNombre());
                tarjeta.setImagenArchivo(tarjetaAnterior.getImagenArchivo());
            }

            // Actualizar la tarjeta en la base de datos
            tarjetaService.actualizarTarjeta(tarjeta);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/tarjeta";
    }

    @GetMapping("/tarjeta/{tarjetaId}")
    public String eliminar(@PathVariable Integer tarjetaId) {
        Tarjeta tarjeta = tarjetaService.obtenerIdTarjeta(tarjetaId);
        
        // Eliminar la imagen asociada a la tarjeta
        eliminarImagen(tarjeta.getImagenNombre());
        
        // Eliminar la tarjeta de la base de datos
        tarjetaService.eliminarTarjeta(tarjetaId);
        
        return "redirect:/admin/tarjeta";
    }


    // Método para generar un nombre único para el archivo de imagen
    private String generarNombreUnico(String nombreOriginal) {
        String[] partesNombre = nombreOriginal.split("\\.");
        String extension = partesNombre[partesNombre.length - 1];
        String nombreBase = partesNombre[0];
        String timestamp = Long.toString(System.currentTimeMillis());
        return nombreBase + "_" + timestamp + "." + extension;
    }

    // Método para eliminar la imagen anterior
    private void eliminarImagen(String nombreImagen) {
        if (nombreImagen != null && !nombreImagen.isEmpty()) {
            Path imagePath = Paths.get(storageLocation + "/" + nombreImagen);
            try {
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
