package com.labrujastore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/contactanos")
public class ContactanosController {

    @GetMapping("")
    public String index_GET() {
        return "contactanos/index";
    }
    
}
