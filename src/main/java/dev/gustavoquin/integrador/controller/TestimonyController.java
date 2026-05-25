package dev.gustavoquin.integrador.controller;

import dev.gustavoquin.integrador.model.entity.Testimony;
import dev.gustavoquin.integrador.model.service.TestimonyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestimonyController {

    @Autowired
    private TestimonyService testimonyService;

    @PostMapping("/testimonios/nuevo")
    public String guardarTestimonio(@ModelAttribute Testimony testimonio) {
        testimonio.setApproved(false);
        testimonyService.save(testimonio);
        return "redirect:/testimonios?enviado";
    }
}