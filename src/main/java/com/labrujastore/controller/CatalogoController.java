package com.labrujastore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController 
{
    @GetMapping("producto")
    public String index()
    {
        return "catalogo/index";
    }
}
