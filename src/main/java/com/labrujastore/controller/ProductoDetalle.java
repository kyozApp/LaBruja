package com.labrujastore.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producto")
public class ProductoDetalle {

    @GetMapping("/detalle")
    public String index() {

        return "producto-detalle/index";
    }
}
