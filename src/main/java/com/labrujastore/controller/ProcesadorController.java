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
import com.labrujastore.entity.Procesador;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.ProcesadorService;

@Controller
@RequestMapping("/admin")
public class ProcesadorController {

    private static String storageLocation = "src/main/resources/static/admin/procesador";

    @Autowired
    private ProcesadorService procesadorService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/procesador")
    public String index(Model model) {
        List<Procesador> procesadores = procesadorService.listarProcesador();
        for (Procesador procesador : procesadores) {
            procesador.setImagenArchivo("http://localhost:8080//admin/procesador/" + procesador.getImagenNombre());
        }
        model.addAttribute("tablaProcesador", procesadores);
        return "admin/procesador/index";
    }

    @GetMapping("/procesador/crear")
    public String crear(Model model) {
        Procesador procesador = new Procesador();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearProcesador", procesador);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/procesador/crear";
    }

    @PostMapping("/procesador/crear")
    public String crear(@ModelAttribute("formularioCrearProcesador") Procesador procesador,
                        @RequestPart("imagen") MultipartFile imagen) {
        try {
            // Generar un nombre único para el archivo de imagen
            String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

            // Establecer el nombre de la imagen en el procesador
            procesador.setImagenNombre(imagenNombre);

            // Obtener la ruta del archivo donde se almacenará la imagen
            Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

            // Copiar los bytes de la imagen al archivo en el sistema de archivos
            Files.copy(imagen.getInputStream(), filePath);

            // Construir la URL de la imagen
            String imageUrl = "http://localhost:8080//admin/procesador/" + imagenNombre;

            // Establecer la URL de la imagen en el procesador
            procesador.setImagenArchivo(imageUrl);

            // Guardar el procesador en la base de datos
            procesadorService.guardarProcesador(procesador);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/procesador";
    }

    @GetMapping("/procesador/editar/{procesadorId}")
    public String editar(@PathVariable Integer procesadorId, Model model) {
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioEditarProcesador", procesadorService.obtenerIdProcesador(procesadorId));
        model.addAttribute("selectorCategorias", categorias);
        return "admin/procesador/editar";
    }

    @PostMapping("/procesador/editar/{procesadorId}")
    public String editar(@PathVariable Integer procesadorId,
                         @ModelAttribute("formularioEditarProcesador") Procesador procesador,
                         @RequestPart("imagen") MultipartFile imagen) {
        try {
            Procesador procesadorAnterior = procesadorService.obtenerIdProcesador(procesadorId);

            // Verificar si se ha proporcionado una nueva imagen
            if (!imagen.isEmpty()) {
                // Eliminar la imagen anterior
                eliminarImagen(procesadorAnterior.getImagenNombre());

                // Generar un nombre único para el archivo de imagen
                String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

                // Establecer el nombre de la nueva imagen en el procesador
                procesador.setImagenNombre(imagenNombre);

                // Obtener la ruta del archivo donde se almacenará la nueva imagen
                Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

                // Copiar los bytes de la imagen al archivo en el sistema de archivos
                Files.copy(imagen.getInputStream(), filePath);

                // Construir la URL de la nueva imagen
                String imageUrl = "http://localhost:8080//admin/procesador/" + imagenNombre;

                // Establecer la URL de la nueva imagen en el procesador
                procesador.setImagenArchivo(imageUrl);
            } else {
                // Si no se proporciona una nueva imagen, mantener la imagen anterior
                procesador.setImagenNombre(procesadorAnterior.getImagenNombre());
                procesador.setImagenArchivo(procesadorAnterior.getImagenArchivo());
            }

            // Actualizar el procesador en la base de datos
            procesadorService.actualizarProcesador(procesador);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/procesador";
    }

    @GetMapping("/procesador/{procesadorId}")
    public String eliminar(@PathVariable Integer procesadorId) {
        Procesador procesador = procesadorService.obtenerIdProcesador(procesadorId);
        
        // Eliminar la imagen asociada al procesador
        eliminarImagen(procesador.getImagenNombre());
        
        // Eliminar el procesador de la base de datos
        procesadorService.eliminarProcesador(procesadorId);
        
        return "redirect:/admin/procesador";
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
